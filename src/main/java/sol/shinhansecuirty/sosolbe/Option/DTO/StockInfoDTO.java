package sol.shinhansecuirty.sosolbe.Option.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StockInfoDTO {
    private String stockName;
    private String stockCode;
    private int currentPrice;
}
