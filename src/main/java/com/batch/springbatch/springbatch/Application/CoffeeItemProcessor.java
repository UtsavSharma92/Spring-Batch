package com.batch.springbatch.springbatch.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CoffeeItemProcessor implements ItemProcessor<Coffee, Coffee> {

	private static final Logger log = LoggerFactory.getLogger(CoffeeItemProcessor.class);

	@Override
	public Coffee process(final Coffee coffee) throws Exception {

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
