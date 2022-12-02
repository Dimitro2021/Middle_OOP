package parser.API;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.Getter;
import lombok.SneakyThrows;
import org.json.JSONObject;

public class BrandFetchAPI implements API {
    @Getter
    private JSONObject data;

    @SneakyThrows
    @Override
    public void getInfo(String domain) {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get(String.format("https://api.brandfetch.io/v2/brands/%s", domain))
                .header("Authorization", "Bearer 7ftz01X5fSSJNeaztKRQjfAzFVUR+XLLDXm44cZEuGA=")
                .asString();
        this.data = new JSONObject(response.getBody());
    }
}
