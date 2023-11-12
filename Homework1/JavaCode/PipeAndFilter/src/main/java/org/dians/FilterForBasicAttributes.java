package org.dians;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class FilterForBasicAttributes implements Filter<List<Object>> {
    @Override
    public List<Object> execute(List<Object> input) throws ElementNotFoundException {
        if (input==null)return null;
        JsonNode featureNode = (JsonNode) input.get(0);
        JsonNode propertiesNode = featureNode.get("properties");
        ObjectNode objectNode = (ObjectNode) input.get(1);
        if(propertiesNode.get("name:en")!=null)
            objectNode.put("name:en", propertiesNode.get("name:en").asText());
        if(propertiesNode.get("addr:city")!=null)
            objectNode.put("addr:city", propertiesNode.get("addr:city").asText());
        if(propertiesNode.get("addr:city:en")!=null)
            objectNode.put("addr:city:en", propertiesNode.get("addr:city:en").asText());
        if(propertiesNode.get("phone")!=null)
            objectNode.put("phone", propertiesNode.get("phone").asText());
        if(propertiesNode.get("opening_hours")!=null)
            objectNode.put("opening_hours", propertiesNode.get("opening_hours").asText());
        return input;
    }
}
