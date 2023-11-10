package org.dians;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static ArrayList<String>categories = (ArrayList<String>) getCategoryList();
    public static Map<String, List<String>> mapTagsAndValues = getTagsAndValuesMap();
    public static File jsonFile=new File("PipeAndFilter\\src\\main\\resources\\exported_data.json");
    public static ObjectMapper objectMapper= new ObjectMapper();
    public static ObjectNode objectNode= JsonNodeFactory.instance.objectNode();
    public static ArrayNode resultJsonArray = objectNode.putArray("ListJson");
    public static List<String> attributes = getAttributeList();

    public static void main(String[] args) throws IOException, ElementNotFoundException {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        JsonNode jsonNode = objectMapper.readTree(jsonFile);
        JsonNode featuresNode = jsonNode.get("features");

        Pipe<JsonNode> pipeArray = new Pipe<>();
        Filter<JsonNode> filterArray = input -> {
            if (input != null && input.isArray())
                return input;
            throw new ElementNotFoundException();
        };
        pipeArray.addFilter(filterArray);

        try {
            featuresNode = pipeArray.runFilters(featuresNode);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        Iterator<JsonNode> iterator = featuresNode.elements();
        while (iterator.hasNext()){
            JsonNode featureNode = iterator.next();
            JsonNode propertiesNode = featureNode.get("properties");
            if (propertiesNode.get("name")==null)continue;
            for (Map.Entry<String, List<String>> entry:mapTagsAndValues.entrySet()){
                Pipe<ObjectNode>pipeNewNode=new Pipe<>();
                if(propertiesNode.get(entry.getKey())!=null && entry.getValue().isEmpty() ||
                        propertiesNode.get(entry.getKey())!=null && entry.getValue().contains(propertiesNode.get(entry.getKey()).asText())){
                    ObjectNode newNode=resultJsonArray.addObject();

                    Filter<ObjectNode>filterId = input -> {
                        input.put("id", propertiesNode.get("@id").asText().split("/")[1]);
                        return input;
                    };
                    pipeNewNode.addFilter(filterId);

                    Filter<ObjectNode>filterName = input -> {
                        input.put("name", propertiesNode.get("name").asText());
                        return input;
                    };
                    pipeNewNode.addFilter(filterName);

                    for( String attribute : attributes) {
                        Filter<ObjectNode> f = input -> {
                            if(propertiesNode.get(attribute)!=null)
                                input.put(attribute, propertiesNode.get(attribute).asText());
                            return input;
                        };
                        pipeNewNode.addFilter(f);
                    }

                    Filter<ObjectNode>filterLongLat = input -> {
                        JsonNode coordinates = featureNode.at("/geometry/coordinates");
                        while(coordinates != null) {
                            if(coordinates.get(0).get(0) == null) break;
                            coordinates = coordinates.get(0);
                        }
                        String longitude = coordinates.get(0).asText();
                        String latitude = coordinates.get(1).asText();
                        input.put("latitude", latitude);
                        input.put("longitude", longitude);
                        return input;
                    };
                    pipeNewNode.addFilter(filterLongLat);


                    for (String category:categories){
                        Filter<ObjectNode>filterCategory = input -> {
                            if(propertiesNode.get(category)!=null){
                                input.put(category, propertiesNode.get(category).asText());
                            }
                            return input;
                        };
                        pipeNewNode.addFilter(filterCategory);
                        Filter<ObjectNode>filterCategoryValue = input -> {
                            propertiesNode.fieldNames().forEachRemaining(key->{
                                if (propertiesNode.get(key).asText().equals(category)){
                                    input.put(key,category);
                                }
                            });
                            return input;
                        };
                        pipeNewNode.addFilter(filterCategoryValue);
                    }

                    pipeNewNode.runFilters(newNode);

                }
            }

        }
        try {
            File outputFile = new File("PipeAndFilter\\src\\main\\resources\\output.json");
            objectMapper.writeValue(outputFile, resultJsonArray);
            System.out.println("ArrayNode written to " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO save to database
//        int counter=0;
//        Iterator<JsonNode>it2=resultJsonArray.iterator();
//        while (it2.hasNext()){
//           it2.next();
//            counter++;
//        }
//
//        System.out.println(resultJsonArray.toPrettyString());
//        System.out.println(counter);
    }
    private static List<String> getAttributeList() {
        List<String> list = new ArrayList<>();
        list.add("name:en");
        list.add("addr:city");
        list.add("addr:city:en");
        list.add("phone");
        list.add("opening_hours");
        return list;
    }
    private static Map<String, List<String>> getTagsAndValuesMap() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("historic", new ArrayList<>());
        map.put("heritage", new ArrayList<>());
        map.put("government", new ArrayList<>());
        map.get("government").add("culture");
        map.put("shop", new ArrayList<>());
        map.get("shop").add("art");
        map.get("shop").add("craft");
        map.put("artwork_type", new ArrayList<>());
        map.put("museum", new ArrayList<>());
        map.put("building", new ArrayList<>());
        map.get("building").add("church");
        map.put("amenity", new ArrayList<>());
        map.get("amenity").add("place_of_worship");
        map.get("amenity").add("arts_centre");
        map.get("amenity").add("public_bookcase");
        map.put("tourism", new ArrayList<>());
        map.get("tourism").add("artwork");
        map.get("tourism").add("gallery");
        map.get("tourism").add("attraction");
        map.get("tourism").add("museum");
        map.get("tourism").add("memorial");
        map.put("cultural", new ArrayList<>());
        map.get("cultural").add("heritage");
        map.put("inscription", new ArrayList<>());
        map.get("inscription").add("yes");
        map.put("man_made", new ArrayList<>());
        map.get("man_made").add("tomb");
        map.put("architecture", new ArrayList<>());
        map.put("denomination", new ArrayList<>());
        map.get("denomination").add("macedonian_orthodox");
        map.put("religion", new ArrayList<>());
        map.get("religion").add("christian");
        return map;
    }
    private static List<String> getCategoryList() {
        List<String> list = new ArrayList<>();
        list.add("historic");
        list.add("heritage");
        list.add("culture");
        list.add("art");
        list.add("museum");
        list.add("church");
        list.add("place_of_worship");
        list.add("arts_centre");
        list.add("craft");
        list.add("artwork");
        list.add("gallery");
        list.add("attraction");
        list.add("memorial");
        list.add("tomb");
        list.add("macedonian_orthodox");
        list.add("christian");
        return list;
    }
}