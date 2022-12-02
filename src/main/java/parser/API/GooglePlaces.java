package parser.API;


import com.google.maps.GeoApiContext;
import com.google.maps.TextSearchRequest;
import com.google.maps.model.PlacesSearchResponse;
import lombok.SneakyThrows;

public class GooglePlaces {
    @SneakyThrows
    static public String searchAddress(String domain){
        String API_KEY = "AIzaSyD90sBPdFulOm9YA_7O7svOzVYVjs-Y-jU";
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(API_KEY)
                .build();
        PlacesSearchResponse placesResponse = new TextSearchRequest(context).query(domain).await();
        String address = placesResponse.results[0].formattedAddress;
        context.shutdown();
        return address;
    }
}

