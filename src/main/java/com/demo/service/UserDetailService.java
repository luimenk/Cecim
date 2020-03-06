package com.demo.service;

import java.util.ArrayList;
import java.util.List;

import com.demo.dao.AppRoleDAO;
import com.demo.dao.AppUserDAO;
import com.demo.model.AppUser;
import com.demo.model.UserRole;
import com.demo.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService{

    @Autowired
    private UserRoleRepository userRoleRepository;

    /*@Autowired
    private AppUserDAO appUserDAO;

    @Autowired
    private AppRoleDAO appRoleDAO;*/

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserRole userRole = userRoleRepository.findByAppUser_UserName(userName);

        System.out.println(userRole.getAppUser().getUserName());

        if (userRole == null) {
            System.out.println("Usuario no encontrado! " + userName);
            throw new UsernameNotFoundException("Usuario " + userName + " no fue encontrado en la base de datos");
        }

        System.out.println("Found User: " + userRole.getAppUser().getUserName());

        String roleNames = userRole.getAppRole().getRoleName();

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleNames);
            grantList.add(grantedAuthority);

        }

        UserDetails userDetails = (UserDetails) new User(userRole.getAppUser().getUserName(),
                userRole.getAppUser().getPassword(),
                grantList);

        return userDetails;
    }

    /*@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.appUserDAO.findUserAccount(userName);

        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }

        System.out.println("Found User: " + appUser);

        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
                appUser.getPassword(), grantList);

        return userDetails;
    }*/
}