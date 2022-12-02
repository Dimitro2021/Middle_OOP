package parser.parsing;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class SearchIcon {
    private final Document doc;
    @Getter
    private String icon;

    public SearchIcon(Document doc) {
        this.doc = doc;
    }

    @SneakyThrows
    public void find() {
        ArrayList<Element> icons = doc.select("head > link[rel=shortcut icon]");
        icon = icons.get(0).attr("abs:href");
    }

}
