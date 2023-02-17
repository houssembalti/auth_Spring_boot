package authentificatrion.auth.Service;

import authentificatrion.auth.domain.Role;
import authentificatrion.auth.domain.User;

import java.util.List;

public interface IUserservice {

  User saveuser(User user);
  Role saveRole(Role role);
  void addRoletouser(String username,String roleName);
  User getuser(String username);
  List<User> getusers();

}
