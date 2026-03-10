package ar.antonella.forohub;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ar.antonella.forohub.repositories.UsuarioRepository;

@SpringBootApplication
@RequiredArgsConstructor
public class ForoHubApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ForoHubApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
/*
		var user = new Usuario();
		user.setUsername("user");
		user.setPassword(passwordEncoder.encode("123"));

		var add = repository.save(user);*/

    }
}
