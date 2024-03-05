package com.vass.dragonball.controller;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vass.dragonball.config.DragonBallConfig;

import io.micrometer.core.instrument.MeterRegistry;

@RestController
@RequestMapping("/application-name")
public class ApplicationNameController {
	
	@Autowired
	private MeterRegistry registry;
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationNameController.class);

	@Autowired
	private DragonBallConfig configuration;
	
	@GetMapping
	public ResponseEntity<String> getAppName(){
		log.info("Getting application name");
		int level = new Random().nextInt(5);
		registry.counter("vass.dragonball.name", "level", (level < 3) ? "Jr": "Sr").increment(level);
		return ResponseEntity.ok("Hola desde "+ configuration.getName());
	}

}
