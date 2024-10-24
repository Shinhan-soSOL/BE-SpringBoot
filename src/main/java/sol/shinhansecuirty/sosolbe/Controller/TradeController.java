package sol.shinhansecuirty.sosolbe.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sol.shinhansecuirty.sosolbe.DTO.BankUpdateDTO;
import sol.shinhansecuirty.sosolbe.DTO.BuyStockResponseDTO;
import sol.shinhansecuirty.sosolbe.Service.TradeService;

@RestController
public class TradeController {
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping("/api/sosol/make-money")
    public ResponseEntity<BuyStockResponseDTO> checkChange(@RequestBody BankUpdateDTO bankUpdateDTO) {
        BuyStockResponseDTO buyStockResponseDTO = tradeService.updateAccount(bankUpdateDTO);
        return ResponseEntity.ok(buyStockResponseDTO);
    }
}
