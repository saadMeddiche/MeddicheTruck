package com.MeddicheTruck.mtmain;

import com.MeddicheTruck.mtcore.embedabbles.FullName;
import com.MeddicheTruck.mtcore.services.EntityService;
import com.MeddicheTruck.mtsecurity.embeddables.Password;
import com.MeddicheTruck.mtsecurity.entities.Permission;
import com.MeddicheTruck.mtsecurity.entities.Role;
import com.MeddicheTruck.mtsecurity.entities.User;
import com.MeddicheTruck.mtsecurity.repositories.PermissionRepository;
import com.MeddicheTruck.mtsecurity.repositories.RoleRepository;
import com.MeddicheTruck.mtsecurity.repositories.UserRepository;
import com.MeddicheTruck.mtsecurity.services.RoleService;
import com.MeddicheTruck.mtsecurity.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@ComponentScan(basePackages = {"com.MeddicheTruck.mtcore" ,"com.MeddicheTruck.mtsecurity"})
@RequiredArgsConstructor
//@ComponentScan(basePackages = {"com.MeddicheTruck.mtcore"})
public class MtMainApplication {


	private final UserRepository userRepository;


	private final RoleRepository roleRepository;


	private final PermissionRepository permissionRepository;

	private final EntityService entityService;

	public static void main(String[] args) {
		SpringApplication.run(MtMainApplication.class, args);
	}

	@Bean
	public ApplicationRunner createDefaultUser(){

		return new ApplicationRunner() {
			@Override
			public void run(ApplicationArguments args) throws Exception {

				createDefaultPermissions();

				crateDefaultRoles();

				createDefaultUsers();

				entityService.getAllEntitiesWithPublicSchema().forEach(System.out::println);
			}
		};
	}

	private void createDefaultPermissions(){

		List<Permission> permissions = new ArrayList<>();
		permissions.add(Permission.builder().id(1L).name("ACCESS_ALL").build());
		permissions.add(Permission.builder().id(2L).name("ACCESS_USER_DASHBOARD").build());
		permissions.add(Permission.builder().id(3L).name("ACCESS_ADMIN_DASHBOARD").build());

		permissionRepository.saveAll(permissions);

	}

	private void crateDefaultRoles(){

		Permission accessAll = permissionRepository.findById(1L).get();
		Permission accessUserDashboard = permissionRepository.findById(2L).get();
		Permission accessAdminDashboard = permissionRepository.findById(3L).get();
		List<Role> roles = new ArrayList<>();

		roles.add(Role.builder()
				.id(1L)
				.name("LEAD_ADMIN")
				.permissions(List.of(accessAll))
				.build());

		roles.add(Role.builder()
				.id(2L)
				.name("ADMIN")
				.permissions(List.of(accessAdminDashboard , accessUserDashboard))
				.build());

		roles.add(Role.builder()
				.id(3L)
				.name("USER")
				.permissions(List.of(accessUserDashboard))
				.build());

		roleRepository.saveAll(roles);
	}

	private void createDefaultUsers(){

		Role leadAdmin = roleRepository.findById(1L).get();
		Role admin = roleRepository.findById(2L).get();
		Role user = roleRepository.findById(3L).get();
		List<User> users = new ArrayList<>();

		users.add(User.builder()
				.id(1L)
				.username("Gamer")
				.fullName(new FullName("Saad" , "" , "Meddiche"))
				.birthDate(LocalDate.parse("2004-01-21"))
				.email("saad2004201@gmail.com")
				.password(new Password("Password#0000"))
				.creationDateAccount(LocalDate.now())
				.roles(List.of(leadAdmin))
				.build());

		users.add(User.builder()
				.id(2L)
				.username("Admin")
				.fullName(new FullName("Khalid" , "" , "Meddiche"))
				.birthDate(LocalDate.parse("2004-01-21"))
				.email("admin0000@gmail.com")
				.password(new Password("Password#0000"))
				.creationDateAccount(LocalDate.now())
				.roles(List.of(admin))
				.build());

		users.add(User.builder()
				.id(3L)
				.username("User")
				.fullName(new FullName("Yassine" , "" , "Meddiche"))
				.birthDate(LocalDate.parse("2004-01-21"))
				.email("yassin0000@gmail.com")
				.password(new Password("Password#0000"))
				.creationDateAccount(LocalDate.now())
				.roles(List.of(user))
				.build());

		userRepository.saveAll(users);
	}

}
