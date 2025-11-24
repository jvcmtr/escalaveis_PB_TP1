package edu.infnet.HistoryService.repositories;

import org.springframework.data.jpa.domain.Specification;

import edu.infnet.HistoryService.models.HistoryEntry;

public class HistorySpecs {

    public static <T extends Comparable<T>> Specification<HistoryEntry> propertyGreaterThan(String PropertyName, T propertyValue){
        return (root, query, cb) -> propertyValue!= null ? cb.greaterThan(root.get(PropertyName), propertyValue) : null;
    }

    public static <T extends Comparable<T>> Specification<HistoryEntry> propertyLessThan(String propertyName, T propertyValue){
        return (root, query, cb) -> propertyValue != null ? cb.lessThan(root.get(propertyName), propertyValue) : null;
    }

    public static <T> Specification<HistoryEntry> propertyMatch(String propertyName, T propertyValue){
        return (root, query, cb) -> propertyValue != null ? cb.equal(root.get(propertyName), propertyValue) : null;
    }
}
