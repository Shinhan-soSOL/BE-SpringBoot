package sol.shinhansecuirty.sosolbe.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sol.shinhansecuirty.sosolbe.DTO.AccountRequestDTO;
import sol.shinhansecuirty.sosolbe.DTO.AccountResponseDTO;
import sol.shinhansecuirty.sosolbe.Entity.Account;
import sol.shinhansecuirty.sosolbe.Entity.SmallChange;
import sol.shinhansecuirty.sosolbe.Entity.Target;
import sol.shinhansecuirty.sosolbe.Entity.User;
import sol.shinhansecuirty.sosolbe.repository.AccountRepository;
import sol.shinhansecuirty.sosolbe.repository.TargetRepository;
import sol.shinhansecuirty.sosolbe.repository.UserRepository;
import sol.shinhansecuirty.sosolbe.repository.SmallChangeRepository;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final TargetRepository targetRepository;
    private final UserRepository userRepository;
    private final SmallChangeRepository smallChangeRepository;

    @Transactional
    public AccountResponseDTO createSoSolInfo(AccountRequestDTO accountRequestDTO) {
        //TODO: Exception 처리하기
        User user = userRepository.findById(accountRequestDTO.getUserId()).orElse(null);
        //TODO : save도 해야하고 현재 저장하는 정보가 맞는지 체크
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

    private static boolean isOnlySaving(AccountRequestDTO accountRequestDTO) {
        return accountRequestDTO.getStockCode().equals("000000");
    }

}
