package prog3fp.llom.forum.Domain;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    @NotEmpty
    @Size(min=8, max=20, message = "Username : 8 to 20 characters")
    @Column(unique = true)
    private String username;

    private String password;
    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;
    @URL
    private String photo;
    private boolean active;
    private String roles;

}
