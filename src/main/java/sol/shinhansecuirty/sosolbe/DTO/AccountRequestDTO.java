package sol.shinhansecuirty.sosolbe.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Data
public class AccountRequestDTO {
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Integer userId;
    @NotNull(message = "은행계좌는 필수입니다.")
    private String bank;
    @NotNull(message = "증권계좌는 필수입니다.")
    private String security;
    @NotNull(message = "잔돈범위 지정은 필수 입니다.")
    private Integer changeRange;
    @NotNull(message = "종목 코드는 필수입니다.")
    @Pattern(regexp = "^[0-9]{6}$", message = "종목 코드는 6자리 숫자여야 합니다.")
    private String stockCode;
    @NotNull(message = "종목명은 필수입니다.")
    private String stockName;
}
