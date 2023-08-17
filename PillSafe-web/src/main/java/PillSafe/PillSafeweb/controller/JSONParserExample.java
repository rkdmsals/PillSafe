package PillSafe.PillSafeweb.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// ... (기타 import 및 코드)

public class JSONParserExample {
    public JSONObject parseJson(String result) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject object = (JSONObject) jsonParser.parse(result);
        return object;
    }

    public JSONArray getItems(JSONObject jsonObject) {
        JSONObject parse_object = (JSONObject) jsonObject.get("body");
        return (JSONArray) parse_object.get("items");
    }
}

