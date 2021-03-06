package com.batch.springbatch.springbatch.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

			log.info("Inside afterJob() method");

			log.info("!!! JOB FINISHED! Time to verify the results");

			String query = "SELECT brand, origin, characteristics FROM coffee";
			jdbcTemplate.query(query, (rs, row) -> new Coffee(rs.getString(1), rs.getString(2), rs.getString(3)))
					.forEach(coffee -> log.info("Found < {} > in the database.", coffee.getBrand()));

		}
	}

}
