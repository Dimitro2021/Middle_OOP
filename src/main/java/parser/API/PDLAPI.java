package parser.API;

import lombok.Getter;
import lombok.SneakyThrows;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class PDLAPI implements API{
    @Getter
    private JSONObject data;

    @SneakyThrows
    @Override
    public void getInfo(String domain) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("https://api.peopledatalabs.com/v5/company/enrich?website=%s&pretty=false", domain)))
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .header("X-API-Key", "1dac5f6fbe6f72d36a2b1492a915f66d57fff571921279b1603a6ea77c441646")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        this.data = new JSONObject(response.body());
    }
}
