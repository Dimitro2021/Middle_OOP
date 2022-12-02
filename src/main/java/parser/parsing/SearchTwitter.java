package parser.parsing;
import lombok.Getter;
import lombok.SneakyThrows;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class SearchTwitter {
    private final Document doc;
    @Getter
    private String twitter;
    public SearchTwitter(Document doc){

        this.doc = doc;
    }
    @SneakyThrows
    public void find(){
        ArrayList<Element> twitterLink = doc.select("a[href^=https://twitter.com/]");
        twitter = twitterLink.get(0).attr("abs:href");
    }

}
