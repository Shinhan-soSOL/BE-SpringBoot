package sol.shinhansecuirty.sosolbe.DTO;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AssetInfoDTO {
    private String stockName;
    private String stockCode;
    private int quantity;
    private int investedAmount;//구매가 총합
    private int profit;//수익
    private int total;//현재 가치 총합(현재가 * 보유수량)
    private double avgPrice;//평단가
    private double profitRatio;
    private int currentPrice;
}
