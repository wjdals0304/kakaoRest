package kakao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KakaoRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaoRestApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
	{
		return builder.sources(KakaoRestApplication.class);
	}
}
