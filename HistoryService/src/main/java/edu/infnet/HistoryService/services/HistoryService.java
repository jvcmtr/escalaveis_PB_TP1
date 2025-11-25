package edu.infnet.HistoryService.services;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import edu.infnet.HistoryService.listeners.RabbitConfig;
import edu.infnet.HistoryService.models.HistoryEntry;
import edu.infnet.HistoryService.repositories.HistoryEntryRepository;
import edu.infnet.HistoryService.repositories.HistorySpecs;

@Service
public class HistoryService {
    @Autowired private HistoryEntryRepository historyEntryRepository;

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public HistoryEntry createEntry( HistoryEntry entry) {
       
        try{
            Specification<HistoryEntry> spec = Specification
                .where(HistorySpecs.propertyMatch("source", entry.getSource()))
                .and(HistorySpecs.propertyMatch("entityName", entry.getEntityName()))
                .and(HistorySpecs.propertyMatch("entityId", entry.getEntityId()));
            var existing = historyEntryRepository
                .findAll(spec, Sort.by("date").descending())
                .getFirst();
            entry.setOldStateJSON(existing.getNewStateJSON());
        }
        catch (Exception e){}
        
        HistoryEntry savedEntry = historyEntryRepository.save(entry);
        return savedEntry;
    }
}
