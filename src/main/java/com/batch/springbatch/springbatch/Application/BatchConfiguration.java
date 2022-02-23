package com.batch.springbatch.springbatch.Application;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfiguration.class);

	@Bean
	public FlatFileItemReader<Coffee> reader() {

		return new FlatFileItemReaderBuilder().name("utsav").resource(new ClassPathResource("coffee-list.csv"))
				.delimited().names(new String[] { "brand", "origin", "characteristics" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper() {
					{
						setTargetType(Coffee.class);
					}
				}).build();

		/*
		 * FlatFileItemReader<Coffee> reader = new FlatFileItemReader<>();
		 * 
		 * reader.setResource(new ClassPathResource("coffee-list.csv"));
		 * reader.setLineMapper(getLineMapper()); reader.setLinesToSkip(1);
		 * 
		 * return reader;
		 */

	}

	@Bean
	public JdbcBatchItemWriter writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO coffee (brand, origin, characteristics) VALUES (:brand, :origin, :characteristics)")
				.dataSource(dataSource).build();
	}
	
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
	    return jobBuilderFactory.get("importUserJob")
	      .incrementer(new RunIdIncrementer())
	      .listener(listener)
	      .flow(step1)
	      .end()
	      .build();
	}
	
	
	@Bean
	public Step step1(JdbcBatchItemWriter writer) {
	    return stepBuilderFactory.get("step1")
	      .<Coffee, Coffee> chunk(10)
	      .reader(reader())
	      .processor(processor())
	      .writer(writer)
	      .build();
	}

	@Bean
	public CoffeeItemProcessor processor() {
	    return new CoffeeItemProcessor();
	}

}
