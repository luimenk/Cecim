package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.demo.model.AppUser;
import com.demo.model.UserRole;
import com.demo.repository.AppUserRepository;
import com.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Buscar el usuario con el repositorio y si no existe lanzar una exepcion
        AppUser usuarios1 = appUserRepository.findByUserName(username);
        List<UserRole> appUser = userRoleRepository.findAllByAppUser_UserName(username);

        //Mapear nuestra lista de Authority con la de spring security
        List grantList = new ArrayList();

        for (int i = 0; i < appUser.size(); i++) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appUser.get(i).getAppRole().getRoleName());
            grantList.add(grantedAuthority);
        }

        //Crear El objeto UserDetails que va a ir en sesión y retornarlo.
        UserDetails user = (UserDetails) new User(usuarios1.getUserName(), usuarios1.getPassword(), grantList);
        return user;
    }
}
