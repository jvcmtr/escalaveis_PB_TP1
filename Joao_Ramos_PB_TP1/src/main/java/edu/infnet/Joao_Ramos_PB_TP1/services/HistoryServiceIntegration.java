package edu.infnet.Joao_Ramos_PB_TP1.services;

import java.io.Serializable;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import edu.infnet.Joao_Ramos_PB_TP1.models.base.BaseEntity;

@Service
public class HistoryServiceIntegration {

    @Autowired private RabbitTemplate rabbitTemplate;

    public <T extends BaseEntity> void entityCreated(T entity){
        var event = buildDTO(entity, entity.getCreatedBy(), EACTIONS.CREATED);
        emmitEvent(event, EACTIONS.CREATED);
    }

    public <T extends BaseEntity> void entityUpdated(T entity ){
        var event = buildDTO(entity, entity.getUpdatedBy(), EACTIONS.UPDATED);
        emmitEvent(event, EACTIONS.UPDATED);
    }

    public <T extends BaseEntity> void entityDeleted(T entity ){
        var event = buildDTO(entity, entity.getDeletedBy(), EACTIONS.DELETED);
        emmitEvent(event, EACTIONS.DELETED);
    }

    private <T extends BaseEntity> DTO<T> buildDTO(T entity, String username, EACTIONS action){
        var body = new DTO<T>();
        body.entityName = entity.getClass().getName();
        body.entityId = entity.getId().toString();
        body.action = action.name();
        body.username = username;
        body.state = entity;
        return body;
    }

    private void emmitEvent(DTO event, EACTIONS action ) {
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE,
                RabbitConfig.ROUTING_KEY,
                event
        );        
    }
}

class DTO<T> {
    public final String source = "Joao_Ramos_PB_TP1 App";
    public String action;
    public String username;
    public String entityName;
    public String entityId;
    public T state;
}

enum EACTIONS{
    CREATED,
    UPDATED,
    DELETED;
}