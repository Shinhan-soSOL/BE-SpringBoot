package sol.shinhansecuirty.sosolbe.DTO;

import lombok.Builder;
import lombok.Data;
import sol.shinhansecuirty.sosolbe.Entity.Account;
import sol.shinhansecuirty.sosolbe.Entity.Target;
import sol.shinhansecuirty.sosolbe.Entity.User;

@Data
@Builder
public class AccountResponseDTO {

    private User user;
    private String stockCode;
    private String stockName;
    private String bankAccount;
    private String securityAccount;
    private Integer bankBalance;
    private Integer securityBalance;
    private Integer balanceSize;

    public static AccountResponseDTO from(Target target, Account bankAccount, Account securityAccount) {
        return AccountResponseDTO.builder()
                .user(target.getUser())
                .stockCode(target.getStockCode())
                .stockName(target.getStockName())
                .bankAccount(bankAccount.getAccountNum())
                .securityAccount(securityAccount.getAccountNum())
                .bankBalance(bankAccount.getBalance())
                .securityBalance(securityAccount.getBalance())
                .balanceSize(securityAccount.getBalanceSize())
                .build();
    }
}
