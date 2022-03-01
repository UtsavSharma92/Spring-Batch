package com.batch.springbatch.springbatch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.zaxxer.hikari.HikariDataSource;

@SpringBootApplication
public class SpringbatchApplication implements CommandLineRunner {

	@Autowired
	DataSource datasource;
	
	private static final Logger log = LoggerFactory.getLogger(SpringbatchApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringbatchApplication.class, args);
		// System.out.println("Data source name:" + datasource);
		
		log.info("run() method completed, application ended");

		/*
		 * ConfigurableApplicationContext cac =
		 * SpringApplication.run(SpringbatchApplication.class, args);
		 * 
		 * //System.out.println("Application name :"+cac.getApplicationName());
		 */
	}

	@Override
	public void run(String... args) throws Exception {

		HikariDataSource ds = (HikariDataSource) datasource;

		System.out.println("Connection pool size:" + ds.getMaximumPoolSize());

		System.out.println("Driver class name:" + ds.getDriverClassName());

		System.out.println("Get user name:" + ds.getUsername());

	}

}
