package edu.infnet.HistoryService.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.infnet.HistoryService.models.DTOs.CreateHistoryDTO;
import edu.infnet.HistoryService.services.HistoryService;

// CREATE, UPDATE, DELETE Mantidos em uma fila unica para que o historico seja internamente consistente
@Service
public class EntityChangedListener {

    @Autowired private HistoryService historyService;

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void handleOrderCreated( CreateHistoryDTO createInfo) {
       
        var entry = createInfo.asHistoryEntry();
        historyService.createEntry(entry);
        
    }
}
