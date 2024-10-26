package sol.shinhansecuirty.sosolbe.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BalanceResponseDTO {
    private int bankBalance;
    private int secBalance;
    private int changeBalance;
    private int balanceSize;
}
