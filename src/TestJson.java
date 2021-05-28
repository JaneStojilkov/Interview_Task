import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;




public class TestJson {

    // Puts JSON objects from url into a JSON array and returns it
    private static JSONArray ParseFromJSONFromURL(String url_string) throws IOException, ParseException {
        StringBuilder in = new StringBuilder();
        URL url = new URL(url_string);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String str;
        while (null != (str = br.readLine())) {
            in.append(str);
        }
        JSONParser parse = new JSONParser();

        return (JSONArray) parse.parse(in.toString());
    }

    //I added the printing of the products in a method for clarity in main method. For arguments it takes the two arrays of products.
    private static void PrintProducts(ArrayList<Product> products_domestic, ArrayList<Product> products_nondomestic){

        Double price_allDomestic = 0.0;
        Double price_allImported = 0.0;
        System.out.println(". Domestic");
        for (Product product : products_domestic) {
            System.out.println(product);
            price_allDomestic += product.getPrice();
        }

        System.out.println(". Imported");
        for (Product product : products_nondomestic) {
            System.out.println(product);
            price_allImported += product.getPrice();
        }

        System.out.println("Domestic cost: $"+price_allDomestic.toString());
        System.out.println("Imported cost: $"+price_allImported.toString());
        System.out.println("Domestic count: "+products_domestic.size());
        System.out.println("Imported count: "+products_nondomestic.size());

    }

    public static void main(String[] args) {
        ArrayList<Product> products_domestic= new ArrayList<>();
        ArrayList<Product> products_nondomestic= new ArrayList<>();
        JSONArray jarray;
        String url = "https://interview-task-api.mca.dev/qr-scanner-codes/alpha-qr-gFpwhsQ8fkY1";

        try {
            jarray = ParseFromJSONFromURL(url);


            for (Object o : jarray) {
                JSONObject jsonobj_1 = (JSONObject) o;
                if (jsonobj_1 != null) {
                    Product p = new Product(jsonobj_1.get("name").toString(),
                            (Boolean) jsonobj_1.get("domestic"),
                            (Double) jsonobj_1.get("price"),
                            (Long) jsonobj_1.get("weight"),
                            jsonobj_1.get("description").toString());

                    if (jsonobj_1.get("domestic").equals(true)) {
                        products_domestic.add(p);
                    } else {
                        products_nondomestic.add(p);
                    }
                }
            }

        Collections.sort(products_domestic);
        Collections.sort(products_nondomestic);
        PrintProducts(products_domestic, products_nondomestic);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

