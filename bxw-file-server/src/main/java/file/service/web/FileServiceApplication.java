package file.service.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Created by wen578351314@gmail.com
 * date 2018/11/29
 */
@SpringBootApplication
@ServletComponentScan
public class FileServiceApplication extends SpringBootServletInitializer {

	protected final static Logger logger = LoggerFactory.getLogger(FileServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FileServiceApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FileServiceApplication.class);
	}
}
