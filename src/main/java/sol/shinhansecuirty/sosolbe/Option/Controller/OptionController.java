package sol.shinhansecuirty.sosolbe.Option.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.shinhansecuirty.sosolbe.Option.DTO.StockInfoDTO;
import sol.shinhansecuirty.sosolbe.Option.Service.OptionService;

import java.util.ArrayList;

@RestController
public class OptionController {

    private final OptionService optionservice;

    public OptionController (OptionService optionService) {
        this.optionservice = optionService;
    }

    @GetMapping("option-price")
    public ResponseEntity<ArrayList<StockInfoDTO>> getPrices() {
        ArrayList<StockInfoDTO> stockInfoDTO = optionservice.getCurrentPrice();
        return ResponseEntity.ok(stockInfoDTO);
    }
}
