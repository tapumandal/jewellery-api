package me.tapumandal.jewellery;

import me.tapumandal.jewellery.service.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
@EnableWebSecurity
public class JewelleryApplication {
	public static void main(String[] args) {
		SpringApplication.run(JewelleryApplication.class, args);
	}

}