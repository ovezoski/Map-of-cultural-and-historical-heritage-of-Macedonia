package org.dians;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.Map;

import static org.dians.Main.mapTagsAndValues;
import static org.dians.Main.resultJsonArray;

public class FilterForIncludingInOutput implements Filter<List<Object>> {
    @Override
    public List<Object> execute(List<Object> input) throws ElementNotFoundException {
        JsonNode featureNode = (JsonNode) input.get(0);
        JsonNode propertiesNode = featureNode.get("properties");
        ObjectNode objectNode = (ObjectNode) input.get(1);
        if (propertiesNode.get("name") == null || propertiesNode.get("name").asText().toLowerCase().contains("гробишта")) return null;
        for (Map.Entry<String, List<String>> entry:mapTagsAndValues.entrySet()){
            if(propertiesNode.get(entry.getKey())!=null && entry.getValue().isEmpty() ||
                    propertiesNode.get(entry.getKey())!=null && entry.getValue().contains(propertiesNode.get(entry.getKey()).asText())){
                if (objectNode==null)
                    objectNode=resultJsonArray.addObject();
                objectNode.put("id", propertiesNode.get("@id").asText().split("/")[1]);
                objectNode.put("name", propertiesNode.get("name").asText());
            }
        }
        if (objectNode==null)return null;
        input.set(1, objectNode);
        return input;
    }
}
