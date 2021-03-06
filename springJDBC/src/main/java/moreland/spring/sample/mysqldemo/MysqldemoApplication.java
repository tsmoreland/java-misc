package moreland.spring.sample.mysqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
@ComponentScan("moreland.spring.sample.mysqldemo")
public class MysqldemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MysqldemoApplication.class, args);
	}

}
