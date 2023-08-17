package PillSafe.PillSafeweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PillSafeWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PillSafeWebApplication.class, args);
	}

}
