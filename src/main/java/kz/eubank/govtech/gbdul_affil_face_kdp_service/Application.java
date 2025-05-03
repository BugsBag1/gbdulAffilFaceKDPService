package kz.eubank.govtech.gbdul_affil_face_kdp_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {"kz.govtech.m11s", "kz.eubank.govtech.gbdul_affil_face_kdp_service"}
)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
