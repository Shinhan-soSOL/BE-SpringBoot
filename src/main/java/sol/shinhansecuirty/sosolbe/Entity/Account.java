package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Builder
@Table(name="account_list")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int accountId;
    private String type;
    @Setter
    private String account;
    @Setter
    private int balance;
    @Setter
    private int balanceSize;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
