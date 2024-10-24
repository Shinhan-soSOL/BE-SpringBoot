package sol.shinhansecuirty.sosolbe.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.shinhansecuirty.sosolbe.DTO.StockInfoDTO;
import sol.shinhansecuirty.sosolbe.Service.OptionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OptionController {

    private final OptionService optionservice;

    public OptionController (OptionService optionService) {
        this.optionservice = optionService;
    }

    @GetMapping("/api/option-price")
    public ResponseEntity<Map<String, List<StockInfoDTO>>> getPrices() {
        List<StockInfoDTO> stockInfoDTOs = optionservice.getCurrentPrice();
        Map<String, List<StockInfoDTO>> response = new HashMap<>();
        response.put("stocks", stockInfoDTOs);
        return ResponseEntity.ok(response);
    }
}
