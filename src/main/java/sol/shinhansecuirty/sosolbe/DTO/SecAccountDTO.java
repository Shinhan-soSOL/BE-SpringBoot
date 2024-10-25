package sol.shinhansecuirty.sosolbe.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SecAccountDTO {
    private String companyName;
    private String accountName;
    private String accountNumber;
}
