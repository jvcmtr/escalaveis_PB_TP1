package edu.infnet.HistoryService.models.DTOs;


import java.time.ZonedDateTime;
import java.util.Map;

import edu.infnet.HistoryService.models.HistoryEntry;
import lombok.Data;
import tools.jackson.databind.ObjectMapper;

@Data
public class CreateHistoryDTO {
    private String source;
    private String entityName;
    private String entityId;
    private String username;
    private String action;
    private Map<String, Object> state;

    public HistoryEntry asHistoryEntry(){
        var e = new HistoryEntry();
        e.setSource(source);
        e.setEntityName(entityName);
        e.setEntityId(entityId);
        e.setUsername(username);
        e.setAction(action);

        var json = new ObjectMapper().writeValueAsString(state);
        e.setNewStateJSON(json);

        var now = ZonedDateTime.now();
        e.setDate(now);

        return e;
    }
}
