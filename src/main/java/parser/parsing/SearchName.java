package parser.parsing;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;

public class SearchName {
    private Document doc;
    @Getter
    private String name;

    public SearchName(Document doc) {
        this.doc = doc;
    }

    @SneakyThrows
    public void find() {

        name = doc.title();
    }

}
