package sol.shinhansecuirty.sosolbe.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sol.shinhansecuirty.sosolbe.DTO.BankUpdateDTO;
import sol.shinhansecuirty.sosolbe.DTO.BuyStockDTO;
import sol.shinhansecuirty.sosolbe.DTO.BuyStockResponseDTO;
import sol.shinhansecuirty.sosolbe.Entity.Account;
import sol.shinhansecuirty.sosolbe.Entity.SmallChange;
import sol.shinhansecuirty.sosolbe.Entity.Target;
import sol.shinhansecuirty.sosolbe.repository.AccountRepository;
import sol.shinhansecuirty.sosolbe.repository.SmallChangeRepository;
import sol.shinhansecuirty.sosolbe.repository.TargetRepository;
import sol.shinhansecuirty.sosolbe.kafka.dto.OrderDto;

@Service
@RequiredArgsConstructor
public class TradeService {

    private final AccountRepository accountRepository;
    private final SmallChangeRepository smallChangeRepository;
    private final TargetRepository targetRepository;
    private final KISService kisService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public BuyStockResponseDTO updateAccount(BankUpdateDTO bankUpdateDTO) {
        //계좌 정보 조회
        Account myBank = accountRepository.findByAccountNumAndType(bankUpdateDTO.getAccountNum(), "은행");
        System.out.println(bankUpdateDTO.getAccountNum());
        System.out.println("차감 전 : " + myBank.getBalance());
        //은행계좌에서 차감
        myBank.setBalance(myBank.getBalance() - bankUpdateDTO.getTradePrice());
        System.out.println("차감 후 : " + myBank.getBalance());
        //잔액에서 잔돈 추출, 은행계좌 잔돈 차감
        int divisor = myBank.getBalanceSize();
        int small = myBank.getBalance() % divisor;
        myBank.setBalance((myBank.getBalance()/divisor)*1000);
        System.out.println("잔돈 : " + small);
        System.out.println("잔돈 추출 후 은행 : " + myBank.getBalance());
        accountRepository.save(myBank);

        //증권계좌 가져와서 잔액 업데이트
        Account mySecurity = accountRepository.findByUserAndType(myBank.getUser(), "증권");
        System.out.println("증권 잔액 변동 전 : " + mySecurity.getBalance());
        mySecurity.setBalance(mySecurity.getBalance() + small);
        System.out.println("증권 잔액 변동 후 : " + mySecurity.getBalance());

        //잔돈 불러와서 업데이트
        SmallChange smallChange = smallChangeRepository.findByAccount(mySecurity);
        System.out.println("잔돈 변동 전 : " + smallChange.getCurrentBalance());
        smallChange.setCurrentBalance(smallChange.getCurrentBalance() + small);
        System.out.println("변동 후 : " + smallChange.getCurrentBalance());
        System.out.println("토탈 잔돈 변동 전 : " + smallChange.getTotal());
        smallChange.setTotal(smallChange.getTotal() + small);
        System.out.println("토탈 잔돈 변동 후 : " + smallChange.getTotal());

        //목표 주식 정보 조회
        Target target = targetRepository.findByUser(mySecurity.getUser());

        //현재가 조회
        double targetPrice = kisService.getCurrentPriceFromKIS(target.getStockCode());
        if((double)smallChange.getCurrentBalance()>targetPrice * 1.01) {
            //카프카 전송
            int quantity = (int) (smallChange.getCurrentBalance()/targetPrice);
            OrderDto orderDto = OrderDto.builder()
                    .userId(mySecurity.getUser().getUserId())
                    .accountId(myBank.getAccountId())
                    .stockCode(target.getStockCode())
                    .quantity(quantity)
                    .currentBalance(smallChange.getCurrentBalance())
                    .build();
            kafkaTemplate.send("order", orderDto);

            BuyStockDTO buyStockDTO = BuyStockDTO.builder()
                    .stockName(target.getStockName())
                    .stockCode(target.getStockCode())
                    .quantity(quantity)
                    .build();

            BuyStockResponseDTO buyStockResponseDTO = BuyStockResponseDTO.builder()
                    .addedChange(small)
                    .isBuy(true)
                    .buyStockDTO(buyStockDTO)
                    .build();
            return buyStockResponseDTO;
        }
        else {
            BuyStockResponseDTO buyStockResponseDTO = BuyStockResponseDTO.builder()
                    .addedChange(small)
                    .isBuy(false)
                    .build();
            return buyStockResponseDTO;
        }
    }
}
