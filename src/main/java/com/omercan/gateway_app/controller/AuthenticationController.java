package com.omercan.gateway_app.controller;

import com.omercan.gateway_app.model.entity.User;
import com.omercan.gateway_app.model.service.AbstractAuthenticationService;
import com.omercan.gateway_app.model.service.AbstractUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// ******10 -> ProductController
// 10. basamağa kadaki kısım, security ile ilgili
// sign-in ile ilgili -> kimliği doğrulanmış kullanıcı için oturum açma ile ilgili
@RequestMapping("api/authentication")
@RestController
public class AuthenticationController
{
    @Autowired
    private AbstractAuthenticationService authenticationService;

    @Autowired
    private AbstractUserService userService;

    // ******2 -> signInJWT'yi üretmek için AbstractAuthenticationService tanımlanır.
    // sign-in (kullanıcı oturum açması)
    // AuthenticationService üzerinden generateJWT() çağrılacak.
    // Bu sebeple önce AbstractAuthenticationService tanımlanır.
    @PostMapping("sign-in")
    public ResponseEntity<String> signIn(@RequestBody User user)
    {
        // JWT (JSON Web Token) -> session(oturum) yerine bu token kullanılır.
        // Token'ı üretebilmek için, AuthenticationService kullanılır.
        String signInJWT = authenticationService.generateJWT(user);

        return new ResponseEntity<>(signInJWT, HttpStatus.OK);
    }

    // ******1
    // sign-up (kullanıcı kaydı)
    @PostMapping("sign-up")
    public ResponseEntity<User> signUp(@RequestBody User user)
    {
        if(userService.findByUsername(user.getUsername()).isPresent())
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
}
