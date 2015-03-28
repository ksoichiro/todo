package models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mindrot.jbcrypt.BCrypt;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends Model {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    @Column(nullable = false)
    private Long createdAt;

    @Column(nullable = false)
    private Long updatedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public static Finder<String, User> find = new Finder<>(String.class, User.class);

    public static User authenticate(String username, String password) {
        User user = find.where().eq("username", username).findUnique();
        if (user == null) {
            return null;
        }
        final boolean matched = BCrypt.checkpw(password, user.password);
        if (matched) {
            return user;
        }
        return null;
    }
}
