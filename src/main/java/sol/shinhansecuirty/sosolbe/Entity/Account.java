package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="account_list")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int accountId;
    private String type;
    @Setter
    private String accountNum;
    @Setter
    private int balance;
    @Setter
    private int balanceSize;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
