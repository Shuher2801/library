package auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.by.library.entity.UserRole;
import it.by.library.entity.Users;
import it.by.library.services.IUserService;
import it.by.library.services.exception.ServiceException;

@Service("authService")
public class AuthenticationService implements UserDetailsService {
	private static Logger log = Logger.getLogger(AuthenticationService.class);
    @Autowired
    private IUserService userService;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
    	log.info("заходит в метод");
        Users user = null;
		try {
			user = userService.getUserByName(userName);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		log.info("достала юзера");
        System.out.println("User : " + user);
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        log.info("возвращает юзера");
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(Users user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        log.info("зашел в метод getGranted");
        for (UserRole userRole : user.getUserRoles()) {
            System.out.println("userRole : " + userRole);
            //authorities.add(new SimpleGrantedAuthority(userRole.getListRole().name()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getType()));
        }
        log.info("прошел метод getGranted");
        System.out.print("authorities :" + authorities);
        return authorities;
    }

}
