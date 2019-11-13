package club.gclmit.chaos.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "club.gclmit.chaos.music.mapper")
public class ChaosMusicApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChaosMusicApiApplication.class, args);
    }

}
