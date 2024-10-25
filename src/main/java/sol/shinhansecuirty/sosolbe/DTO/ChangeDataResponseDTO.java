package sol.shinhansecuirty.sosolbe.DTO;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChangeDataResponseDTO {
    private int changeSum;//누적 잔돈
    private int changeAmount;//잔돈 잔량
    private int investedAmount;//투자된 금액
    private int totalValue;//보유주식 총가치
    private double profitRatio;//수익률
    private StockInfoDTO goal;//목표주식
    private List<AssetInfoDTO> myStocks;
}
