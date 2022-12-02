package parser.info;


import parser.API.BrandFetchAPI;
import parser.API.GooglePlaces;
import parser.API.PDLAPI;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import parser.parsing.*;
import parser.searchViaAPI.*;

import java.net.InetAddress;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Info {
    @Id
    private String domain;
    private String name = null;
    private String twitter = null;
    private String facebook = null;
    private String logo = null;
    private String icon = null;
    private String size = null;
    private String address = null;

    @SneakyThrows
    public Info(String domain) {
        try {
            InetAddress inetAddress = InetAddress.getByName(domain);
            this.domain = domain;

            PDLAPI pdl = new PDLAPI();
            pdl.getInfo(domain);

            Parser parser = new Parser(domain);
            Document doc = parser.parse();

            BrandFetchAPI brandFetch = new BrandFetchAPI();
            brandFetch.getInfo(domain);

            getAndSetName(doc);
            getAndSetFacebook(doc, brandFetch.getData(), pdl.getData());
            getAndSetTwitter(doc, brandFetch.getData(), pdl.getData());
            getAndSetIcon(doc, brandFetch.getData());
            getAndSetLogo(brandFetch.getData());
            getAndSetSize(pdl.getData());
            getAndSetAddress(domain);
        } catch (Exception e) {
            this.domain = "not found";
        }
    }

    private void getAndSetName(Document doc) {
        SearchName name = new SearchName(doc);
        name.find();
        this.name = name.getName();
    }


    private void getAndSetFacebook(Document doc, JSONObject brandFetchObj, JSONObject pdlObj) {
        facebook = new GetFacebook().searchViaBrandFetch(brandFetchObj);
        if (facebook.equals("not found")) {
            facebook = new GetFacebook().searchViaPDL(pdlObj);
        }
        if (facebook.equals("not found")) {
            try {
                SearchFacebook facebook = new SearchFacebook(doc);
                facebook.find();
                this.facebook = facebook.getFacebook();
            } catch (Exception e) {
                this.facebook = "not found";
            }
        }
    }

    private void getAndSetLogo(JSONObject brandFetchObj) {
        logo = new GetLogo().searchwithBrandFetch(brandFetchObj);
    }

    private void getAndSetAddress(String domain) {
        try {
            address = GooglePlaces.searchAddress(domain);
        } catch (Exception e) {
            address = "not found";
        }
    }
    private void getAndSetIcon(Document document, JSONObject brandFetch) {
        icon = new GetIcon().searchViaBrandFetch(brandFetch);
        if (icon.equals("not found")) {
            try {
                SearchIcon icon = new SearchIcon(document);
                icon.find();
                this.icon = icon.getIcon();
            } catch (Exception e) {
                this.icon = "not found";
            }
        }
    }

    private void getAndSetTwitter(Document doc, JSONObject brandFetchObj, JSONObject pdlObj) {
        twitter = new GetTwitter().searchViaBrandFetch(brandFetchObj);
        if (twitter.equals("not found")) {
            twitter = new GetTwitter().searchViaPDL(pdlObj);
        }
        if (twitter.equals("not found")) {
            try {
                SearchTwitter twitter = new SearchTwitter(doc);
                twitter.find();
                this.twitter = twitter.getTwitter();
            } catch (Exception e) {
                this.twitter = "not found";
            }
        }
    }
    private void getAndSetSize(JSONObject pdl) {

        size = new GetSize().getEmployees(pdl);
    }



    public static void main(String[] args) {
        Info info = new Info("google.com");
        System.out.println(info);
    }
}
