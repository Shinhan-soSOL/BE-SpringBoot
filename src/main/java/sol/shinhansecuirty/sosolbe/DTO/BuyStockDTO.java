package sol.shinhansecuirty.sosolbe.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BuyStockDTO {
    private String stockName;
    private String stockCode;
    private int quantity;
}
