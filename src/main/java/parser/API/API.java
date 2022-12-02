package parser.API;

import lombok.SneakyThrows;

public interface API {

    @SneakyThrows
    void getInfo(String domain);
}
