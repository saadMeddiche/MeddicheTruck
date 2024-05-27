package com.MeddicheTruck.mtsecurity.commandLineRunner;

import com.MeddicheTruck.mtcore.embedabbles.FullName;
import com.MeddicheTruck.mtsecurity.embeddables.Password;
import com.MeddicheTruck.mtsecurity.entities.Authority;
import com.MeddicheTruck.mtsecurity.entities.User;
import com.MeddicheTruck.mtsecurity.enums.BaseAuthority;
import com.MeddicheTruck.mtsecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 2)
public class UserInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        userRepository.saveAll(generateUsers());
        System.out.println("Users initialized");
    }

    private List<User> generateUsers(){

        List<User> users = new ArrayList<>();

        users.add(User.builder()
                .id(1L)
                .username("Gamer")
                .fullName(new FullName("Saad" , "" , "Meddiche"))
                .birthDate(LocalDate.parse("2004-01-21"))
                .email("saad2004201@gmail.com")
                .password(new Password("Password#0000"))
                .creationDateAccount(LocalDate.now())
                .authorities(List.of(new Authority(BaseAuthority.ACCESS_ALL)))
                .build());

        users.add(User.builder()
                .id(2L)
                .username("Admin")
                .fullName(new FullName("Khalid" , "" , "Meddiche"))
                .birthDate(LocalDate.parse("2004-01-21"))
                .email("admin0000@gmail.com")
                .password(new Password("Password#0000"))
                .creationDateAccount(LocalDate.now())
                .authorities(List.of(
                        new Authority(BaseAuthority.ACCESS_USER_DASHBOARD),
                        new Authority(BaseAuthority.ACCESS_ADMIN_DASHBOARD)
                ))
                .build());

        users.add(User.builder()
                .id(3L)
                .username("User")
                .fullName(new FullName("Yassine" , "" , "Meddiche"))
                .birthDate(LocalDate.parse("2004-01-21"))
                .email("yassin0000@gmail.com")
                .password(new Password("Password#0000"))
                .creationDateAccount(LocalDate.now())
                .authorities(List.of(new Authority(BaseAuthority.ACCESS_USER_DASHBOARD)))
                .build());

        return users;
    }

}
