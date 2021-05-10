package br.com.zupacademy.proposta.healthcheck;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckPersonalizado extends AbstractHealthIndicator {
	
	@Override
	public void doHealthCheck(Health.Builder bldr) throws Exception {
		boolean running = true;
		if (running) {
			bldr.up();
		} else
			bldr.down();
	}
}
