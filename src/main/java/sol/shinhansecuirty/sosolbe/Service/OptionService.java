package sol.shinhansecuirty.sosolbe.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sol.shinhansecuirty.sosolbe.DTO.AccountListDTO;
import sol.shinhansecuirty.sosolbe.DTO.SecAccountDTO;
import sol.shinhansecuirty.sosolbe.DTO.StockInfoDTO;
import sol.shinhansecuirty.sosolbe.Entity.SecurityAccount;
import sol.shinhansecuirty.sosolbe.Entity.User;
import sol.shinhansecuirty.sosolbe.repository.SecurityAccountRepository;
import sol.shinhansecuirty.sosolbe.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OptionService {

    private KISService kisService;
    private UserRepository userRepository;
    private SecurityAccountRepository securityAccountRepository;

    public OptionService(KISService kisService) {
        this.kisService = kisService;
    }

    public List<StockInfoDTO> getCurrentPrice() {
        List<StockInfoDTO> stockInfos = new ArrayList<>();
        List<String> stockCodes = Arrays.asList("005360", "079160", "047040", "073240", "055550");
        List<String> stockNames = Arrays.asList("모나미", "CJ CGV", "대우건설", "금호타이어", "신한지주");
        for(int i=0;i<stockCodes.size();i++) {
            int currentPrice = kisService.getCurrentPriceFromKIS(stockCodes.get(i));
            StockInfoDTO stockInfoDTO = StockInfoDTO.builder()
                    .stockName(stockNames.get(i))
                    .stockCode(stockCodes.get(i))
                    .currentPrice(currentPrice)
                    .build();
            stockInfos.add(stockInfoDTO);
        }
        return stockInfos;
    }

    public AccountListDTO findAccounts(int userId) {
        Optional<User> user = userRepository.findById(userId);
        List<SecurityAccount> securityAccounts = securityAccountRepository.findByUser(user.get());
        List<SecAccountDTO> secAccountDTOS = new ArrayList<>();
        for(int i=0;i<securityAccounts.size();i++) {
            SecAccountDTO secAccountDTO = SecAccountDTO.builder()
                    .companyName("신한투자증권")
                    .accountName(securityAccounts.get(i).getAccountName())
                    .accountNumber(securityAccounts.get(i).getAccountNumber())
                    .build();
            secAccountDTOS.add(secAccountDTO);
        }
        return AccountListDTO.builder().secAccounts(secAccountDTOS).build();
    }

}
