package sol.shinhansecuirty.sosolbe.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name="user")
public class User {
    @Id
    private int userId;

    public User(int userId) {
        this.userId = userId;
    }
}
