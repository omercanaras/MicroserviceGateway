package com.omercan.gateway_app.model.service;

import com.omercan.gateway_app.model.entity.User;
import com.omercan.gateway_app.security.jwt.JWTProvidable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;

public abstract class AbstractAuthenticationService
{
    // ******7 JWTProvidable ve -> implementasyonu JWTProvider tanımlanır.
    @Autowired
    protected JWTProvidable jwtProvider;


    // ******5 -> AuthenticationManager implementasyon nesnesini
    // döndüren @Bean tanımlanır. (SecurityConfig'de)
    // (bkz. dependency injection)
    @Autowired
    protected AuthenticationManager authenticationManager;


    // ******3 -> AuthenticationService tanımlanır.
    /**
     *
     * @param user
     *      sign-in işleminde bilgileri girilen kullanıcı
     *
     * @return
     *      kimliği doğrulanmış kullanıcı için oluşturulan JWT
     *      eğer kimlik doğrulama hatası meydana gelirse, null
     */
    public abstract String generateJWT(User user);
}
