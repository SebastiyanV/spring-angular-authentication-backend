package springangularjwt.auth.web.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginServiceModel {

    private String email;
    private String password;
}
