package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="small_change")
public class SmallChange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int changeId;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    @Setter
    private int total;
    @Setter
    private int currentBalance;
}
