package parser.searchViaAPI;

import lombok.SneakyThrows;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetFacebook implements SearchViaAPI {
    @Override @SneakyThrows
    public String searchViaBrandFetch(JSONObject json_data) {
        JSONArray arr = json_data.getJSONArray("links");
        for(int i = 0; i < arr.length(); ++i){
            if (arr.getJSONObject(i).getString("name").equals("facebook")){
                return arr.getJSONObject(i).getString("url");
            }
        }
        return "not found";
    }

    @Override
    public String searchViaPDL(JSONObject api1) {
        try {
            return api1.getString("facebook_url");
        } catch (JSONException e) {
            return "not found";
        }
    }
}
