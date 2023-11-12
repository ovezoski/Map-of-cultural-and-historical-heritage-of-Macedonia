package org.dians;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class CategoryFilters {
    public static void process(Pipe<List<Object>>pipeNewNode){
        for (String category:Main.getCategoryList()){
            Filter<List<Object>>filterCategory = input -> {
                if (input==null)return null;
                JsonNode featureNode = (JsonNode) input.get(0);
                JsonNode propertiesNode = featureNode.get("properties");
                ObjectNode objectNode = (ObjectNode) input.get(1);
                if(propertiesNode.get(category)!=null){
                    objectNode.put(category, propertiesNode.get(category).asText());
                }
                return input;
            };
            pipeNewNode.addFilter(filterCategory);

            Filter<List<Object>>filterCategoryValue = input -> {
                if (input==null)return null;
                JsonNode featureNode = (JsonNode) input.get(0);
                JsonNode propertiesNode = featureNode.get("properties");
                ObjectNode objectNode = (ObjectNode) input.get(1);
                propertiesNode.fieldNames().forEachRemaining(key->{
                    if (propertiesNode.get(key).asText().equals(category)){
                        objectNode.put(key,category);
                    }
                });
                return input;
            };
            pipeNewNode.addFilter(filterCategoryValue);
    }

    }
}
