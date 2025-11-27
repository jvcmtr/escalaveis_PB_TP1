package edu.infnet.HistoryService.controllers;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.infnet.HistoryService.models.DTOs.CreateHistoryDTO;
import edu.infnet.HistoryService.models.HistoryEntry;
import edu.infnet.HistoryService.repositories.HistoryEntryRepository;
import edu.infnet.HistoryService.repositories.HistorySpecs;
import edu.infnet.HistoryService.services.HistoryService;

@RestController
@RequestMapping("/history")
public class HistoryEntryController {

    @Autowired private HistoryEntryRepository historyEntryRepository;
    @Autowired private HistoryService historyService;

    // Create a new HistoryEntry
    @PostMapping
    public ResponseEntity<HistoryEntry> createHistoryEntry(@RequestBody CreateHistoryDTO createInfo) {
        
        // Delega implementação para o event listener para manter o comportamento.
        var entry = createInfo.asHistoryEntry();
        var saved = historyService.createEntry(entry);
        return ResponseEntity.ok(saved);
    }

    @GetMapping()
    public List<HistoryEntry> getAll(
            @RequestParam(required = false) ZonedDateTime dateGreaterThan,
            @RequestParam(required = false) ZonedDateTime dateLessThan,
            @RequestParam(required = false) String source,
            @RequestParam(required = false) String entityName,
            @RequestParam(required = false) Long entityId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String action,
            @RequestParam(defaultValue = "false") Boolean asc
    ) {

        Specification<HistoryEntry> spec = Specification
            .where(HistorySpecs.propertyGreaterThan("date", dateGreaterThan))
            .and(HistorySpecs.propertyLessThan("date", dateLessThan))
            .and(HistorySpecs.propertyMatch("source", source))
            .and(HistorySpecs.propertyMatch("entityName", entityName))
            .and(HistorySpecs.propertyMatch("entityId", entityId))
            .and(HistorySpecs.propertyMatch("username", username))
            .and(HistorySpecs.propertyMatch("action", action));

        Sort sort = asc? 
            Sort.by("date").ascending()
            :Sort.by("date").descending();
        
        return historyEntryRepository.findAll(spec, sort);
    }

}
