package sol.shinhansecuirty.sosolbe.DTO;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AccountListDTO {
    List<SecAccountDTO> secAccounts;
}
