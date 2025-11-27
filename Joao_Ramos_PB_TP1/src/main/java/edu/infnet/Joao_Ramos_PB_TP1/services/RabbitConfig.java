package edu.infnet.Joao_Ramos_PB_TP1.services;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    public static final String EXCHANGE = "history.exchange";
    public static final String ROUTING_KEY = "history.record";
    public static final String QUEUE = "history.queue";

    @Bean
    public DirectExchange historyExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    // Mantidos em uma fila unica para que o historico seja internamente consistente
    public Queue historyQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public Binding historyBinding() {
        return BindingBuilder
                .bind(historyQueue())
                .to(historyExchange())
                .with(RabbitConfig.ROUTING_KEY);
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        var converter = new Jackson2JsonMessageConverter();
        converter.setCreateMessageIds(false);
        return converter ;
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

}
