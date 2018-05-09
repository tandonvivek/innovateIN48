package com.fis.epo.ui.accelerator.api.jpa;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fis.epo.ui.accelerator.api.ApiBasePackageClass;

@SpringBootApplication(scanBasePackageClasses = { ApiBasePackageClass.class })
@EntityScan(basePackageClasses = { ApiBasePackageClass.class })
public class ApiServicesJpaApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().sources(ApiServicesJpaApplication.class).bannerMode(Banner.Mode.OFF).run(args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}

}