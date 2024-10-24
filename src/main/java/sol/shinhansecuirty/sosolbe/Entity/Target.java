package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Builder
@Table(name="target")
public class Target {

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private String stockName;
    private String stockCode;
}
