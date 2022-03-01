package com.batch.springbatch.springbatch.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Coffee {

	public static final Logger log = LoggerFactory.getLogger(Coffee.class);

	private String brand;
	private String origin;
	private String characteristics;

	public Coffee() {

	}

	public Coffee(String brand, String origin, String characteristics) {

		log.info("Coffee constructor called:");
		log.info("Setting value from excel file for brand:" + brand);
		log.info("Setting value from excel file for origin:" + origin);
		log.info("Setting value from excel file for characteristics:" + characteristics);

		this.brand = brand;
		this.origin = origin;
		this.characteristics = characteristics;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {

		log.info("Setting brand name:");

		this.brand = brand;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		
		log.info("Setting origin name:");
		
		this.origin = origin;
	}

	public String getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(String characteristics) {
		
		log.info("Setting characteristics:");
		
		this.characteristics = characteristics;
	}

}
