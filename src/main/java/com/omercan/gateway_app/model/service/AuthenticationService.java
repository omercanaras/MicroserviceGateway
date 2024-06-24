package com.omercan.gateway_app.model.service;

import com.omercan.gateway_app.model.entity.User;
import com.omercan.gateway_app.security.model.UserPrincipal;
import com.omercan.gateway_app.utility.Util;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

//  ******4 -> generateJWT() metodunun ne yaptığını anlatmak üzere
//  AbstractAuthenticationService'de ilgili unsurlar tanımlanır.
//  Önce authenticationManager tanımlandı.
@Service
public class AuthenticationService extends AbstractAuthenticationService
{
    // ******9 -> AuthenticationController'a geçildi ve sign-in tamamlandı.
    @Override
    public String generateJWT(User user)
    {
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()); // ******9.4

        try
        {
            Authentication authentication = authenticationManager.authenticate(authenticationToken); // ******9.3

            // Bu nesneyi oluşturabilmek için, Authentication tipinde nesne gerekir.
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal(); // ******9.2

            // token üretilirken, kimliği doğrulanmış kullanıcı kullanılır.
            // Yani user ile usePrinciple üretilir.
            return jwtProvider.generateToken(userPrincipal); // ******9.1
        }
        catch (AuthenticationException e)
        {
            Util.showGeneralExceptionInfo(e);

            return null;
        }
    }
}
