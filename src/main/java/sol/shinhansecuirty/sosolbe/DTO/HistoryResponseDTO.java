package sol.shinhansecuirty.sosolbe.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import sol.shinhansecuirty.sosolbe.Entity.History;

@Data
@Builder
@AllArgsConstructor
public class HistoryResponseDTO {
    private List<History> tradings;
}
