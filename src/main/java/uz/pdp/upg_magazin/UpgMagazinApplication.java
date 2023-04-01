package uz.pdp.upg_magazin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@SpringBootApplication
public class UpgMagazinApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpgMagazinApplication.class, args);
    }

}
