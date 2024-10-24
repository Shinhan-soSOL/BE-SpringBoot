package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@Table(name="asset")
public class Asset {

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    private String stockCode;
    private String stockName;
    private int quantity;
    private double averagePrice;
}
