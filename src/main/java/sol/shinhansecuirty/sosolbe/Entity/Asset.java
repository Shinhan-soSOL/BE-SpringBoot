package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
@Table(name="asset")
public class Asset {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int assetId;
    private String stockCode;
    private String stockName;
    private int quantity;
    private double averagePrice;
}
