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
    public static Pipe<List<Object>> pipeNewNode = addPipeAndFilters();
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

        int countOfObjects=0;
        Iterator<JsonNode> iterator = featuresNode.elements();
        while (iterator.hasNext()){
            JsonNode featureNode = iterator.next();
            ObjectNode newNode=null;
            List<Object>list=new ArrayList<>();
            list.add(featureNode);
            list.add(newNode);
            list=pipeNewNode.runFilters(list);
            if (list!=null)
                countOfObjects++;
        }
        try {
            File outputFile = new File("PipeAndFilter\\src\\main\\resources\\output.json");
            objectMapper.writeValue(outputFile, resultJsonArray);
            System.out.println("New .json file with " + countOfObjects + " objects written to "+ outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Pipe<List<Object>> addPipeAndFilters() {
        Filter<List<Object>>filterForBasicAttributes=new FilterForBasicAttributes();
        Filter<List<Object>>filterForCoordinates=new FilterForCoordinates();
        Filter<List<Object>>filterIncludingInOutput=new FilterForIncludingInOutput();

        Pipe<List<Object>>pipeNewNode=new Pipe<>();
        pipeNewNode.addFilter(filterIncludingInOutput);
        pipeNewNode.addFilter(filterForBasicAttributes);
        CategoryFilters.process(pipeNewNode);
        pipeNewNode.addFilter(filterForCoordinates);
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