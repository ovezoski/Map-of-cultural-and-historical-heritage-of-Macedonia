package org.dians;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class FilterForName implements Filter<List<Object>> {
    @Override
    public List<Object> execute(List<Object> input) throws ElementNotFoundException {
        JsonNode featureNode = (JsonNode) input.get(0);
        JsonNode propertiesNode = featureNode.get("properties");
        ObjectNode objectNode = (ObjectNode) input.get(1);
        objectNode.put("name", propertiesNode.get("name").asText());
        return input;
    }
}
