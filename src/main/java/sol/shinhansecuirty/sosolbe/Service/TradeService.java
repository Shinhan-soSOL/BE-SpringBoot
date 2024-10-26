package sol.shinhansecuirty.sosolbe.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sol.shinhansecuirty.sosolbe.DTO.BalanceResponseDTO;
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

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
        //은행계좌에서 차감
        myBank.setBalance(myBank.getBalance() - bankUpdateDTO.getTradePrice());
        //잔액에서 잔돈 추출, 은행계좌 잔돈 차감
        int divisor = myBank.getBalanceSize();
        int small = myBank.getBalance() % divisor;
        myBank.setBalance((myBank.getBalance()/divisor)*1000);
        accountRepository.save(myBank);

        //증권계좌 가져와서 잔액 업데이트
        Account mySecurity = accountRepository.findByUserAndType(myBank.getUser(), "증권");
        mySecurity.setBalance(mySecurity.getBalance() + small);

        //잔돈 불러와서 업데이트
        SmallChange smallChange = smallChangeRepository.findByAccount(mySecurity);
        smallChange.setCurrentBalance(smallChange.getCurrentBalance() + small);
        smallChange.setTotal(smallChange.getTotal() + small);

        BalanceResponseDTO balanceResponseDTO = BalanceResponseDTO.builder()
                .bankBalance(myBank.getBalance())
                .secBalance(mySecurity.getBalance())
                .changeBalance(smallChange.getCurrentBalance())
                .balanceSize(myBank.getBalanceSize())
                .build();

        //목표 주식 정보 조회
        Target target = targetRepository.findByUser(mySecurity.getUser());

        //현재가 조회
        double targetPrice = kisService.getCurrentPriceFromKIS(target.getStockCode());
        if(isJangTime() && (double)smallChange.getCurrentBalance()>targetPrice * 1.01) {
            //카프카 전송
            int quantity = (int) (smallChange.getCurrentBalance()/targetPrice);
            OrderDto orderDto = OrderDto.builder()
                    .userId(mySecurity.getUser().getUserId())
                    .accountId(mySecurity.getAccountId())
                    .stockCode(target.getStockCode())
                    .stockName(target.getStockName())
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
                    .balance(balanceResponseDTO)
                    .isBuy(true)
                    .buyStockDTO(buyStockDTO)
                    .build();
            return buyStockResponseDTO;
        }
        else {
            BuyStockResponseDTO buyStockResponseDTO = BuyStockResponseDTO.builder()
                    .addedChange(small)
                    .balance(balanceResponseDTO)
                    .isBuy(false)
                    .build();
            return buyStockResponseDTO;
        }
    }

    private boolean isJangTime() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.toLocalDate().atTime(LocalTime.of(9, 0));// 09:00
        LocalDateTime end = now.toLocalDate().atTime(LocalTime.of(15, 20));// 15:20

        // 현재 시간이 주어진 범위 내에 있는지 확인
        boolean isInTimeRange = (now.isAfter(start) && now.isBefore(end)) || now.equals(start) || now.equals(end);

        DayOfWeek dayOfWeek = now.getDayOfWeek();
        boolean isWeekday = (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY);

        return (isInTimeRange && isWeekday);
    }
}
