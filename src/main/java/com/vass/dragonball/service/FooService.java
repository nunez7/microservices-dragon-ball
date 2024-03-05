package com.vass.dragonball.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;

@Service
public class FooService {
	
	@Autowired
	private Tracer tracer;
	
	private static final Logger log = LoggerFactory.getLogger(FooService.class);

	public void printLog() {
		Span newSpan = tracer.nextSpan().name("newSpan");
		try (Tracer.SpanInScope ws = this.tracer.withSpan(newSpan.start())){
			log.info("Test log");
		} finally{
			newSpan.end();
		}
	}

}
