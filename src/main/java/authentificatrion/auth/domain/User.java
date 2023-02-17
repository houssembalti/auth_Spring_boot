package authentificatrion.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private  Long id;
  private String name;
  private String username;
  private  String password;
  @ManyToMany(fetch = FetchType.EAGER )
  private List<Role> roles= new ArrayList<>();
}
