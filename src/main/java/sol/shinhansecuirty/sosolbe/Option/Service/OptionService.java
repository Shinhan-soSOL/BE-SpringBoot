package sol.shinhansecuirty.sosolbe.Option.Service;

import org.springframework.stereotype.Service;
import sol.shinhansecuirty.sosolbe.Option.DTO.StockInfoDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OptionService {

    private KISService kisService;

    public OptionService(KISService kisService) {
        this.kisService = kisService;
    }

    public List<StockInfoDTO> getCurrentPrice() {
        List<StockInfoDTO> stockInfos = new ArrayList<>();
        List<String> stockCodes = Arrays.asList("005360", "079160", "047040", "073240", "055550");
        List<String> stockNames = Arrays.asList("모나미", "CJ CGV", "대우건설", "금호타이어", "신한지주");
        for(int i=0;i<stockCodes.size();i++) {
            int currentPrice = kisService.getCurrentPriceFromKIS(stockCodes.get(i));
            StockInfoDTO stockInfoDTO = StockInfoDTO.builder()
                    .stockName(stockNames.get(i))
                    .stockCode(stockCodes.get(i))
                    .currentPrice(currentPrice)
                    .build();
            stockInfos.add(stockInfoDTO);
        }
        return stockInfos;
    }
}
