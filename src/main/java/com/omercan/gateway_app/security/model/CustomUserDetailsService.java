package com.omercan.gateway_app.security.model;

import com.omercan.gateway_app.model.entity.User;
import com.omercan.gateway_app.model.service.AbstractUserService;
import com.omercan.gateway_app.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private AbstractUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username)
    {
        User user = new User();

        try
        {
            // "Bu kullanıcı adındaki kullanıcı yoksa" durumu, orElseThrow() metodu ile yönetildi.
            user = userService.findByUsername(username).orElseThrow(()
                    -> new UsernameNotFoundException("User with " + username + " is not found."));
        }
       catch (UsernameNotFoundException e)
       {
           Util.showGeneralExceptionInfo(e);
       }

        return new UserPrincipal(user.getUserId(), user.getUsername(), user.getPassword());
    }
}
