package edu.infnet.HistoryService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import edu.infnet.HistoryService.models.HistoryEntry;

@Repository
public interface HistoryEntryRepository extends JpaRepository<HistoryEntry, Long>, JpaSpecificationExecutor<HistoryEntry> {
    
}
