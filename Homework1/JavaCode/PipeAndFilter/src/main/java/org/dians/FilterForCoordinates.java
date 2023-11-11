package org.dians;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;

public class FilterForCoordinates implements Filter<List<Object>> {
    @Override
    public List<Object> execute(List<Object> input) throws ElementNotFoundException {
        JsonNode featureNode = (JsonNode) input.get(0);
        ObjectNode objectNode = (ObjectNode) input.get(1);
        JsonNode coordinates = featureNode.at("/geometry/coordinates");
        while(coordinates != null) {
            if(coordinates.get(0).get(0) == null) break;
            coordinates = coordinates.get(0);
        }
        String longitude = coordinates.get(0).asText();
        String latitude = coordinates.get(1).asText();
        objectNode.put("latitude", latitude);
        objectNode.put("longitude", longitude);
        return input;
    }
}
