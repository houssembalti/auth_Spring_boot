package authentificatrion.auth;

import authentificatrion.auth.Service.IUserservice;
import authentificatrion.auth.domain.Role;
import authentificatrion.auth.domain.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.ArrayList;


@SpringBootApplication
public class AuthApplication  {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
  @Bean
  BCryptPasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Bean
  CommandLineRunner run (IUserservice iUserservice){
    return args -> {
      iUserservice.saveRole(new Role(null,"Role_User"));
      iUserservice.saveRole(new Role(null,"Role_Manager"));
      iUserservice.saveRole(new Role(null,"Role_Admin"));
      iUserservice.saveRole(new Role(null,"Role_superAdmin"));

      iUserservice.saveuser((new User(null,"JHON travolta","jhon","1234",new ArrayList<>())));
      iUserservice.saveuser((new User(null,"Will smith","will","1234",new ArrayList<>())));
      iUserservice.saveuser((new User(null,"jim carrey","jim","1234",new ArrayList<>())));
      iUserservice.saveuser((new User(null,"houssem balti","houssem","1234",new ArrayList<>())));


      iUserservice.addRoletouser("jhon", "Role_User");
      iUserservice.addRoletouser("jhon", "Role_Manager");
      iUserservice.addRoletouser("will", "Role_Manager");
      iUserservice.addRoletouser("jim", "Role_Admin");
      iUserservice.addRoletouser("houssem", "Role_Admin");
      iUserservice.addRoletouser("houssem", "Role_superAdmin");
      iUserservice.addRoletouser("houssem", "Role_User");


    };
  }


}
