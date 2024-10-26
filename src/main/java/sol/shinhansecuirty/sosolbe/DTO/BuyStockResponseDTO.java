package sol.shinhansecuirty.sosolbe.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BuyStockResponseDTO {
    private int addedChange;
    private BalanceResponseDTO balance;
    private boolean isBuy;
    private BuyStockDTO buyStockDTO;
}
