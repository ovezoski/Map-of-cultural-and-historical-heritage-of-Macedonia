package org.dians;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class FilterForCity implements Filter<List<Object>> {
    @Override
    public List<Object> execute(List<Object> input) throws ElementNotFoundException {
        JsonNode featureNode = (JsonNode) input.get(0);
        JsonNode propertiesNode = featureNode.get("properties");
        ObjectNode objectNode = (ObjectNode) input.get(1);
        if(propertiesNode.get("addr:city")!=null)
            objectNode.put("addr:city", propertiesNode.get("addr:city").asText());
        return input;
    }
}
