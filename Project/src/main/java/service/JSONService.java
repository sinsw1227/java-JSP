package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.Iterator;

public class JSONService {
    public String parseJson(InputStream inputStream) {
    	try {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(inputStream);

        // 최상위 키 동적으로 추출
        Iterator<String> fieldNames = rootNode.fieldNames();
        if (fieldNames.hasNext()) {
            String topLevelKey = fieldNames.next(); // 최상위 키 가져오기
            System.out.println("JSONSercvice parseJson() >> topLevelKey : "+ topLevelKey);
            JsonNode rowNode = rootNode.path(topLevelKey).path("row");

            StringBuilder main = new StringBuilder();
            main.append("[");
            rowNode
            .forEach(node->{
            	System.out.println("JSON parsing >> "+node.toString());
            	if(node.path("TRDSTATEGBN").asText().equals("01") && !node.path("X").asText().equals(""))
            		main.append(String.format("{\"name\":\"%s\", \"site\":\"%s\",%s},"
            				, node.path("BPLCNM").asText(), node.path("RDNWHLADDR").asText().equals("")?node.path("SITEWHLADDR").asText():node.path("RDNWHLADDR").asText()
            				, CoordinateService.change(node.path("Y").asText(),node.path("X").asText())));
            });
            return main.deleteCharAt(main.length()-1).append("]").toString();
        }
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return "error";
    }
}