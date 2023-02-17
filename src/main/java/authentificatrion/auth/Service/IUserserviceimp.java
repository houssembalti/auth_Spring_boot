package authentificatrion.auth.Service;

import authentificatrion.auth.domain.Role;
import authentificatrion.auth.domain.User;
import authentificatrion.auth.repo.Rolerepo;
import authentificatrion.auth.repo.Userrepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class IUserserviceimp implements  IUserservice, UserDetailsService {
 private final PasswordEncoder passwordEncoder;
  @Autowired
  private  Userrepo userrepo;
  @Autowired
  private Rolerepo rolerepo;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   User user=userrepo.findByUsername(username);
   if (user==null){
     log.error("user not found!");
     throw new UsernameNotFoundException("user not found in database!");
   }
   else {
     log.info("user found! :{}",username);
   }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
   user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
   return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
  }
  @Override
  public User saveuser(User user) {
    log.info("saving new user {} to the database",user.getName());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userrepo.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    log.info("saving new role{} to the database",role.getName());
    return rolerepo.save(role);
  }

  @Override
  public void addRoletouser(String username, String roleName) {
    log.info("adding role {} to user{} ",roleName,username);
    User user = userrepo.findByUsername(username);
    Role role = rolerepo.findByName(roleName);
    user.getRoles().add(role);

  }

  @Override
  public User getuser(String username) {
    log.info("fetching user {}",username);
    return userrepo.findByUsername(username);
  }

  @Override
  public List<User> getusers() {
    log.info("fetching all users");
    return userrepo.findAll();
  }


}
