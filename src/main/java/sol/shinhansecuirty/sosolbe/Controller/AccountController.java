package sol.shinhansecuirty.sosolbe.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sol.shinhansecuirty.sosolbe.DTO.AccountRequestDTO;
import sol.shinhansecuirty.sosolbe.DTO.HistoryResponseDTO;
import sol.shinhansecuirty.sosolbe.DTO.ResponseDTO;
import sol.shinhansecuirty.sosolbe.Service.AccountService;

@RestController
@RequestMapping("/api/sosol")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/accounts")
    public ResponseEntity<ResponseDTO> makeSoSolAccount(@Valid @RequestBody AccountRequestDTO accountRequestDTO) {

        if(accountService.createSoSolInfo(accountRequestDTO) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO("201","등록되었습니다."));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO("400","요청을 정보를 확인해주세요."));

    }

    @GetMapping("/history/{userId}")
    public ResponseEntity<HistoryResponseDTO> getChangeInvestHistory(@PathVariable Integer userId) {
        //TODO:: 예외처리 필요!
        HistoryResponseDTO historyResponseDTO = accountService.findChangeHistory(userId);
        return ResponseEntity.status(HttpStatus.OK).body(historyResponseDTO);
    }
}
