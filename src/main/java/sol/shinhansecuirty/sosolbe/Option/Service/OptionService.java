package sol.shinhansecuirty.sosolbe.Option.Service;

import org.springframework.stereotype.Service;
import sol.shinhansecuirty.sosolbe.Option.DTO.StockInfoDTO;

import java.util.ArrayList;

@Service
public class OptionService {

    public ArrayList<StockInfoDTO> getCurrentPrice() {
        ArrayList<StockInfoDTO> stockInfos = new ArrayList<>();
        for(int i=0;i<5;i++) {
            StockInfoDTO stockInfoDTO = StockInfoDTO.builder().build();
            stockInfos.add(stockInfoDTO);
        }
        return stockInfos;
    }
}
