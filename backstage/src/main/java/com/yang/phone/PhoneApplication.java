package com.yang.phone;

import javafx.application.Application;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.SpringVersion;

import javax.sql.DataSource;

@SpringBootApplication
@MapperScan("com.yang.phone.mapper")
public class PhoneApplication {
	public static void main(String[] args) {
		SpringApplication.run(PhoneApplication.class, args);
	}
}

