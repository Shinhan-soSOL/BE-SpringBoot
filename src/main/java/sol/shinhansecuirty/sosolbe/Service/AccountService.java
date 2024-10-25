package sol.shinhansecuirty.sosolbe.Service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sol.shinhansecuirty.sosolbe.DTO.*;
import sol.shinhansecuirty.sosolbe.Entity.*;
import sol.shinhansecuirty.sosolbe.repository.*;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TargetRepository targetRepository;
    private final UserRepository userRepository;
    private final SmallChangeRepository smallChangeRepository;
    private final HistoryRepository historyRepository;
    private final AssetRepository assetRepository;
    private final KISService kisService;

    @Transactional
    public AccountResponseDTO createSoSolInfo(AccountRequestDTO accountRequestDTO) {
        //TODO: Exception 처리하기
        User user = userRepository.findById(accountRequestDTO.getUserId()).orElse(null);
        if(!isOnlySaving(accountRequestDTO)) {
            Target target = Target.builder()
                    .user(user)
                    .stockCode(accountRequestDTO.getStockCode())
                    .stockName(accountRequestDTO.getStockName())
                    .build();

            Account bankAccount = Account.builder()
                    .user(user)
                    .type("은행")
                    .accountNum(accountRequestDTO.getBank())
                    .balance(1_000_000)
                    .balanceSize(0)
                    .build();

            Account securityAccount = Account.builder()
                    .user(user)
                    .type("증권")
                    .accountNum(accountRequestDTO.getSecurity())
                    .balance(1_000_000)
                    .balanceSize(accountRequestDTO.getChangeRange())
                    .build();

            SmallChange smallChange = SmallChange.builder()
                    .account(securityAccount)
                    .total(0)
                    .currentBalance(0)
                    .build();


            targetRepository.save(target);
            accountRepository.save(bankAccount);
            accountRepository.save(securityAccount);
            smallChangeRepository.save(smallChange);

            return AccountResponseDTO.from(target,bankAccount,securityAccount);
        }
        return null;
    }

    public HistoryResponseDTO findChangeHistory(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<History> historys =  historyRepository.findByUser(user);

        return HistoryResponseDTO.builder()
                .tradings(historys)
                .build();
    }

    private static boolean isOnlySaving(AccountRequestDTO accountRequestDTO) {
        return accountRequestDTO.getStockCode().equals("000000");
    }

    public ChangeDataResponseDTO findChangeAndAsset(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        Target target = targetRepository.findByUser(user);
        Account secAccount = accountRepository.findByUserAndType(user, "증권");
        SmallChange smallChange = smallChangeRepository.findByAccount(secAccount);
        List<Asset> assetList = assetRepository.findByAccount(secAccount);

        int sumOfPurchase = 0;
        int sumOfCurrent = 0;
        List<AssetInfoDTO> assetInfoDTOs = new ArrayList<>();

        StockInfoDTO targetInfo = StockInfoDTO.builder()
                .stockCode(target.getStockCode())
                .stockName(target.getStockName())
                .currentPrice(kisService.getCurrentPriceFromKIS(target.getStockCode()))
                .build();

        for(int i=0;i<assetList.size();i++) {
            AssetInfoDTO assetInfoDTO = getAssetInfo(assetList.get(i));
            sumOfPurchase += assetInfoDTO.getInvestedAmount();
            sumOfCurrent += assetInfoDTO.getTotal();
            assetInfoDTOs.add(assetInfoDTO);
        }
        double profitRatio = ceiling((double) (sumOfCurrent-sumOfPurchase)/sumOfPurchase);

        ChangeDataResponseDTO changeDataResponseDTO = ChangeDataResponseDTO.builder()
                .changeSum(smallChange.getTotal())
                .changeAmount(smallChange.getCurrentBalance())
                .investedAmount(smallChange.getTotal()- smallChange.getCurrentBalance())
                .totalValue(sumOfCurrent)
                .profitRatio(profitRatio)
                .goal(targetInfo)
                .myStocks(assetInfoDTOs)
                .build();

        return changeDataResponseDTO;

    }

    private AssetInfoDTO getAssetInfo(Asset asset) {

        String parsedStockCode = asset.getStockCode().replaceAll("A", "");
        double doubleInvestedAmount =  asset.getQuantity() * asset.getAveragePrice();
        int investedAmount = (int) Math.ceil(doubleInvestedAmount);
        int quantity = asset.getQuantity();
        int currentPrice = kisService.getCurrentPriceFromKIS(parsedStockCode);
        int currentTotal = currentPrice * quantity;
        int profit = currentTotal-investedAmount;
        double profitRatio = ceiling((double) profit /investedAmount);

        AssetInfoDTO assetInfoDTO = AssetInfoDTO.builder()
                .stockCode(parsedStockCode)
                .stockName(asset.getStockName())
                .quantity(quantity)
                .investedAmount(investedAmount)
                .total(currentTotal)
                .profit(profit)
                .avgPrice(asset.getAveragePrice())
                .profitRatio(profitRatio)
                .currentPrice(currentPrice)
                .build();

        return assetInfoDTO;
    }

    private double ceiling(double input) {
        return (double) Math.round(input * 100) / 100; // 소수점 셋째 자리에서 올림
    }
}
