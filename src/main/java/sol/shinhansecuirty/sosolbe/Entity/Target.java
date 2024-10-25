package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="target")
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int targetId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    private String stockName;
    private String stockCode;
}
