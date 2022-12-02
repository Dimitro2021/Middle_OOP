package parser.searchViaAPI;

import lombok.SneakyThrows;
import org.json.JSONObject;

public interface SearchViaAPI {
    @SneakyThrows
    String searchViaBrandFetch(JSONObject json_data);
    String searchViaPDL(JSONObject json_data);
}
