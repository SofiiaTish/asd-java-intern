package team.asd.config;

import com.antkorwin.xsync.XSync;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"team.asd"})
@MapperScan({"team.asd.mapper"})
public class XSyncConfig {

	@Bean
	public XSync<Integer> xSync() {
		return new XSync<>();
	}
}
