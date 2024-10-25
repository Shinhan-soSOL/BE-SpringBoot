package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SecurityAccount {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int securityAccountId;
    private String accountNumber;
    private String accountName;
}
