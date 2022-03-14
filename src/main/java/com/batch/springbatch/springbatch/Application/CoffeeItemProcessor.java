package com.batch.springbatch.springbatch.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoffeeItemProcessor implements ItemProcessor<Coffee, Coffee> {
	
	@Autowired
	BatchConfiguration fr;

	private static final Logger log = LoggerFactory.getLogger(CoffeeItemProcessor.class);

	@Override
	public Coffee process(final Coffee coffee) throws Exception {
		
		
		log.info("Get logging class name:"+log.getClass());
		log.info("Get logging class name:"+log.getName());
		
		log.info("Calling reader method 1st time:"+fr.reader());
		
		log.info("Calling reader method 2nd time:"+fr.reader());
		
		
		
		
		log.info("Inside Coffee process() method");
		
		
		log.info("get coffee brand through constructor:"+ new Coffee().getBrand());		
		

		String brand = coffee.getBrand().toUpperCase();
		String origin = coffee.getOrigin().toUpperCase();
		String chracteristics = coffee.getCharacteristics().toUpperCase();

		Coffee transformedCoffee = new Coffee(brand, origin, chracteristics);
		log.info("Converting ( {} ) into ( {} )", coffee, transformedCoffee);

		return transformedCoffee;
	}

}
