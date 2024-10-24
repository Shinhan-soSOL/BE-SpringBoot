package sol.shinhansecuirty.sosolbe.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CurrentPriceDTO {

    private Output output;

    @Getter
    @NoArgsConstructor
    public static class Output {
        private Integer stck_prpr;
    }
}