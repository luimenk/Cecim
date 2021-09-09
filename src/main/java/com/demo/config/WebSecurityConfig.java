package com.demo.config;

import javax.sql.DataSource;

import com.demo.service.UserDetailService;
import com.demo.service.UserDetailsServiceImpl;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();
        http.authorizeRequests().antMatchers("/grafica1").access("hasAnyRole('SUPERUSUARIO', 'LABORATORISTA', 'ADMINISTRADOR', 'ESPECIALISTA')");
        http.authorizeRequests().antMatchers("/dashboard1").access("hasRole('SUPERUSUARIO')");
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
        http.authorizeRequests().and().formLogin()
                .loginProcessingUrl("/j_spring_security_check")
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");

        // Configuración del RECUERDAME
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h

    }*/

    // Token stored in Table (Persistent_Logins)
    /*@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }*/

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    //Necesario para evitar que la seguridad se aplique a los resources
    //como los css, imagenes y javascripts
    String[] resources = new String[]{
            "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**","/dist/**","plugins/**","/assets/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/","/index", "/nameCorreo", "/nameCodigo", "/nameContrasena", "/recuperaCuenta/**").permitAll()
//                .antMatchers("/**").access("hasRole('SUPERUSUARIO')")
//                .antMatchers("/registroCliente*").access("hasRole('ROLE_REGISTRARCLIENTES')")
//                .antMatchers("/mostrarClientes*").access("hasRole('ROLE_CONSULTARCLIENTES')")
//                .antMatchers("/registroMetodo*").access("hasRole('REGISTRARMETODOS')")
//                .antMatchers("/mostrarMetodos*").access("hasRole('CONSULTARMETODOS')")
//                .antMatchers("/registerUsuario*").access("hasRole('REGISTRARUSUARIOS')")
//                .antMatchers("/listUsuario*").access("hasRole('CONSULTARUSUARIOS')")
//                .antMatchers("/registroMaquina*").access("hasRole('REGISTRAREQUIPO')")
//                .antMatchers("/mostrarMaquinas*").access("hasRole('CONSULTAREQUIPO')")
//                .antMatchers("/registerSolicituedServicio*").access("hasRole('REGISTRARSOLICITUDSERVICIOCLIENTE')")
//                .antMatchers("/listSolicitudServicio*").access("hasRole('CONSULTARSOLICITUDSERVICIOCLIENTE')")
//                .antMatchers("/listSolicitudServicioPagos*").access("hasRole('PAGOSSOLICITUDSERVICIOCLIENTE')")
//                .antMatchers("/listCotizacionContrato*").access("hasRole('CONSULTARCOTIZACIONCONTRATO')")
//                .antMatchers("/listSolicitudServicioInterno*").access("hasRole('CONSULTARSOLICITUDSERVICIOINTERNO')")
//                .antMatchers("/registerValidacion*").access("hasRole('REGISTRARRETENCION')")
//                .antMatchers("/listRecepcionValidacion*").access("hasRole('CONSULTARRETENCION')")
//                .antMatchers("/lecturaQR*").access("hasRole('REGISTRARLECTURAENSAYO')")
//                .antMatchers("/listEnsayos*").access("hasRole('CONSULTARLISTAENSAYOS')")
//                .antMatchers("/registroCliente*").access("hasAnyRole('USER') or hasRole('ADMIN')")
//                .antMatchers("/registroMetodo*", "/mostrarMetodos*").access("hasAnyRole('SUPERUSUARIO')")
                .anyRequest().authenticated()
                .and().exceptionHandling().accessDeniedPage("/403")
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");

        // Configuración del RECUERDAME
        http.authorizeRequests().and() //
                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
        db.setDataSource(dataSource);
        return db;
    }
}
