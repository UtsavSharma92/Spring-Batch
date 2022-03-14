package com.batch.springbatch.springbatch;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
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

		log.info("Connection pool size:" + ds.getMaximumPoolSize());
		log.info("Driver class name:" + ds.getDriverClassName());
		log.info("Connection pool size:" + ds.getMaximumPoolSize());

	}

}
