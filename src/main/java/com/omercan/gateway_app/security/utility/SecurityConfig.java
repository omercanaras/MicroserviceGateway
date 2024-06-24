package com.omercan.gateway_app.security.utility;

import com.omercan.gateway_app.security.jwt.JWTAuthenticationFilter;
import com.omercan.gateway_app.utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Value("${service.security.secure-key-username}")
    private String secureKeyUsername;

    @Value("${service.security.secure-key-password}")
    private String secureKeyPassword;

    @Autowired
    private UserDetailsService userDetailsService;

    // ******22
    /*
        kaynaklar arası paylaşım için:
        CORS (cross origin resource sharing) politikasi

        CORS konfigurasyonu ile
            izin verilen kaynaklar,
            izin verilen metotlar,
            ve izin verilen yollari belirleyecegiz.

        ornegin: Burasi sayesinde, uygulama yalnizca GET istegi izni verilebilir.
                Belirli bir alan adı varsa, hostlar yalnizca ona gore kisitlanabilir.

        CORS tarafindan bir istege izin verilmiyorsa, istek engellenir.

        Bu bir Java bean (bkz. @Bean annotion) oldugu icin,
        bundan yeni nesneler olusturulup tum aplikasyon bazinda kullanilabilir.

        or: https://stackoverflow.com/questions/40286549/spring-boot-security-cors
     */
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                registry.addMapping("/**")
                        .allowedOrigins("*") // or: localhost
                        .allowedMethods("*"); // or: POST, GET etc.
            }
        };
    }

    /* ******21
        HTTP filtre zincirine eklendi.
     */
    @Bean
    public JWTAuthenticationFilter createJWTAuthenticationFilter()
    {
        return new JWTAuthenticationFilter();
    }

    // ör: https://www.baeldung.com/security-none-filters-none-access-permitAll
    // ör: https://stackoverflow.com/questions/30819337/multiple-antmatchers-in-spring-security
    // ******20
    // Session kullanılmayacak. JSON Web Token kullanılacak.
    // yetkilendirme
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        try
        {
            httpSecurity.csrf().disable();

            /*
            SessionCreationPolicy.ALWAYS -> Framework, session yoksa mutlaka oluşturur.

            SessionCreationPolicy.NEVER -> Framework, yeni bir session hiçbir zaman oluşturmaz.
            Ne var ki, eğer halihazırda bir session varsa, onu kullanır.

            SessionCreationPolicy.IFREQUIRED -> Framework, gerekliyse session oluşturur. (varsayılan hâl)

            SessionCreationPolicy.STATELESS -> Framework, yeni bir session hiçbir zaman oluşturmaz ve kullanmaz.
            */
            httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            /*
            İstemciler (clients), web sunucusu tarafından desteklenen istekleri
            öğrenmek için OPTIONS isteği (HttpMethod.OPTIONS) yollar.
            Burada, web sunucusu tarafından desteklenen isteklere izin verildi.
            */
            httpSecurity.authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS).permitAll()
                    .antMatchers("/api/authentication/**").permitAll()
                    .anyRequest().authenticated();
            // bkz. AuthenticationController -> @RequestMapping("api/authentication")


            /*
                Güvenlik filtre zincirinde, UsernamePasswordAuthenticationFilter'ın öncesine
                JWTAuthenticationFilter eklendi.
             */
            httpSecurity.addFilterBefore(createJWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        }
        catch (IllegalArgumentException e)
        {
            Util.showGeneralExceptionInfo(e);
        }
        catch (Exception e)
        {
            Util.showGeneralExceptionInfo(e);
        }
    }

    // kimlik doğrulama
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        try
        {
            auth.userDetailsService(userDetailsService).passwordEncoder(createPasswordEncoder());
        }
        catch (Exception e)
        {
            Util.showGeneralExceptionInfo(e);
        }
    }

    // ******6 -> AbstractAuthenticationService
    @Override
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        try
        {
            return super.authenticationManagerBean();
        }
        catch (Exception e)
        {
            Util.createGeneralExceptionInfo(e);
            return null;
        }
    }

    @Bean
    public PasswordEncoder createPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
