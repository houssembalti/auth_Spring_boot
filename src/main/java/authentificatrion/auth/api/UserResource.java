package authentificatrion.auth.api;

import authentificatrion.auth.Service.IUserservice;
import authentificatrion.auth.domain.Role;
import authentificatrion.auth.domain.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(origins= {"*"})
public class UserResource {
  private final IUserservice iUserservice;
  @GetMapping("/users")
  public ResponseEntity<List<User>> getuser() {
    return ResponseEntity.ok().body(iUserservice.getusers());
  }
  @PostMapping("/user/save")
  public ResponseEntity<User> saveuser(@RequestBody User user) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
    return ResponseEntity.created(uri).body(iUserservice.saveuser(user));
  }
  @PostMapping("/role/save")
  public ResponseEntity<Role> saverole(@RequestBody Role role) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
    return ResponseEntity.ok().body(iUserservice.saveRole(role));
  }
  @PostMapping("/role/addtouser")
  public ResponseEntity<?> addroletouser(@RequestBody RoleUserform roleUserform) {
    iUserservice.addRoletouser(roleUserform.getUsername(), roleUserform.getRoleName());
    return ResponseEntity.ok().build();
  }


}
@Data
class  RoleUserform{
  private String username;
  private String roleName;
}
