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
    public static Map<String, List<String>> mapTagsAndValues = getTagsAndValuesMap();
    public static File jsonFile=new File("PipeAndFilter\\src\\main\\resources\\exported_data.json");
    public static ObjectMapper objectMapper= new ObjectMapper();
    public static ObjectNode objectNode= JsonNodeFactory.instance.objectNode();
    public static ArrayNode resultJsonArray = objectNode.putArray("ListJson");

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

        Pipe<List<Object>> pipeNewNode = addPipeAndFilters();

        Iterator<JsonNode> iterator = featuresNode.elements();
        while (iterator.hasNext()){
            JsonNode featureNode = iterator.next();
            JsonNode propertiesNode = featureNode.get("properties");
            ObjectNode newNode=null;
            if (propertiesNode.get("name") == null) continue;
            for (Map.Entry<String, List<String>> entry:mapTagsAndValues.entrySet()){
                if(propertiesNode.get(entry.getKey())!=null && entry.getValue().isEmpty() ||
                        propertiesNode.get(entry.getKey())!=null && entry.getValue().contains(propertiesNode.get(entry.getKey()).asText())){
                    if (newNode==null)
                        newNode=resultJsonArray.addObject();
                    List<Object>list=new ArrayList<>();
                    list.add(featureNode);
                    list.add(newNode);
                    pipeNewNode.runFilters(list);
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

    private static Pipe<List<Object>> addPipeAndFilters() {
        Filter<List<Object>>filterForId=new FilterForId();
        Filter<List<Object>>filterForName=new FilterForName();
        Filter<List<Object>>filterForNameEn=new FilterForNameEn();
        Filter<List<Object>>filterForCoordinates=new FilterForCoordinates();
        Filter<List<Object>>filterForCity=new FilterForCity();
        Filter<List<Object>>filterForCityEn=new FilterForCityEn();
        Filter<List<Object>>filterForPhone=new FilterForPhone();
        Filter<List<Object>>filterForOpeningHours=new FilterForOpeningHours();

        Pipe<List<Object>>pipeNewNode=new Pipe<>();
        pipeNewNode.addFilter(filterForId);
        pipeNewNode.addFilter(filterForName);
        pipeNewNode.addFilter(filterForNameEn);
        pipeNewNode.addFilter(filterForCoordinates);
        pipeNewNode.addFilter(filterForCity);
        pipeNewNode.addFilter(filterForCityEn);
        pipeNewNode.addFilter(filterForPhone);
        pipeNewNode.addFilter(filterForOpeningHours);
        CategoryFilters.process(pipeNewNode);
        return pipeNewNode;
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
    public static List<String> getCategoryList() {
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