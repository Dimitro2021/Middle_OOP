package parser.parsing;

import lombok.Getter;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class SearchFacebook {
    private final Document doc;
    @Getter
    private String facebook;

    public SearchFacebook(Document doc) {
        this.doc = doc;
    }

    @SneakyThrows
    public void find() {
        ArrayList<Element> facebookLink = doc.select("a[href^=https://www.facebook.com/]");
        facebook = facebookLink.get(0).attr("abs:href");
    }

}
