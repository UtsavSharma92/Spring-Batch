package com.batch.springbatch.springbatch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.batch.springbatch.springbatch.domain.Customer;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class BatchController {

	private static Logger log = LoggerFactory.getLogger(BatchController.class);

	@RequestMapping(value = "/springbatch/hello", method = RequestMethod.POST)
	public String helloWorld(@RequestBody Customer customer) throws JsonMappingException {
		
		RequestMappingHandlerMapping rq = new RequestMappingHandlerMapping();

		log.info("Checking how json is converted to POJO");

		log.info("Printing first name:" + customer.getFirstName());

		log.info("Printing last name:" + customer.getLastName());

		return "ok";

	}

}
