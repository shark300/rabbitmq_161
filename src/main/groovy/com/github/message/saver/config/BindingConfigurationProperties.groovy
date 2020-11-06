package com.github.message.saver.config

import groovy.transform.builder.Builder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties("service.integration.binding")
@Component
@Builder
class BindingConfigurationProperties {
	BindingConfiguration incoming = new BindingConfiguration()

	@Builder
	static class BindingConfiguration {
		String exchangeName
		String queueName
		DeadLetterBindingConfiguration deadLetter = new DeadLetterBindingConfiguration()

		static class DeadLetterBindingConfiguration {
			String exchangeName
			String queueName
			String routingKey
		}
	}
}
