package parser.searchViaAPI;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetLogo{
    @SneakyThrows
    public String searchwithBrandFetch(JSONObject json_data) {
        JSONArray arr = json_data.getJSONArray("logos");
        for(int i = 0; i < arr.length(); ++i){
            if (arr.getJSONObject(i).getString("type").equals("logo")){
                return arr.getJSONObject(i).getJSONArray("formats").getJSONObject(0).getString("src");
            }
        }
        return "not found";
    }
}
