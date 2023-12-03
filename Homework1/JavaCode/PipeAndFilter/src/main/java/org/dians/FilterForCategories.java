package org.dians;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class FilterForCategories implements Filter<List<Object>>{
    public List<Object> execute(List<Object> input){
        if (input==null)return null;
        JsonNode featureNode = (JsonNode) input.get(0);
        JsonNode propertiesNode = featureNode.get("properties");
        ObjectNode objectNode = (ObjectNode) input.get(1);
        for (String category:Main.getCategoryList()){
                if(propertiesNode.get(category)!=null){
                    objectNode.put(category, propertiesNode.get(category).asText());
                }
                propertiesNode.fieldNames().forEachRemaining(key->{
                    if (propertiesNode.get(key).asText().equals(category)){
                        objectNode.put(key,category);
                    }
                });
        }
        return input;
    }
}
