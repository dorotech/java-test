package com.br.dorotech.app.configs;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.ErrorHandler;

import javax.annotation.PostConstruct;

/**
 * Configuration class for connection with SQS queues (consumer)
 */
@Configuration
@EnableJms
@DependsOn("SQSProviderConfiguration")
@Slf4j
public class SQSJmsConsumerConfiguration implements ErrorHandler {

    private SQSConnectionFactory connectionFactory;

    private AmazonSQS amazonSQS;

    public SQSJmsConsumerConfiguration(AmazonSQS amazonSQS) {
        this.amazonSQS = amazonSQS;
    }

    @PostConstruct
    public void init() {
        connectionFactory = createSQSConnectionFactory();
    }

    private SQSConnectionFactory createSQSConnectionFactory() {
        return new SQSConnectionFactory(new ProviderConfiguration(), amazonSQS);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setErrorHandler(this);
        return factory;
    }

    @Bean
    public JmsTemplate defaultJmsTemplate() {
        return new JmsTemplate(connectionFactory);
    }

    @Override
    public void handleError(Throwable t) {
        log.error("Listener SQS error has been thrown");
        log.error("SQS_ERROR_LOCAL_MESSAGE: {}", t.getLocalizedMessage());
        log.error("SQS_ERROR_MESSAGE: {}", t.getMessage());
    }
}