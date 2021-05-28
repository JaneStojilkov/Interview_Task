public class Product implements Comparable<Product> {
    String name;
    Boolean domestic;
    Double price;
    Long weight;
    String description;

    public Double getPrice() {
        return price;
    }

    public Product(String name, Boolean domestic, Double price, Long weight, String description) {
        this.name = name;
        this.domestic = domestic;
        this.price = price;
        this.weight = weight;
        this.description = description;
    }

    //We override the compareTo function so we can sort with Collections.sort which uses merge sort.
    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        String s = "";
        if(weight!=null){
                 s = "... "+ name + "\n" +
                "    Price: $"+price.toString() + "\n" +
                "    "+ description +
                "    Weight: "+weight.toString()
                ;
        }
        if (weight == null && description.length()<30)
                    s= "... "+ name + "\n" +
                    "    Price: $"+price.toString() + "\n" +
                    "    "+ description +
                    "    Weight: N/A"
                    ;
        else if (weight == null && description.length()>30)
                    s= "... "+ name + "\n" +
                    "    Price: $"+price.toString() + "\n" +
                    "    "+ description.substring(0, 30)+"..." + "\n" +
                    "    Weight: N/A";
        else if (weight!=null && description.length()>30)
                    s= "... "+ name + "\n" +
                    "    Price: $"+price.toString() + "\n" +
                    "    "+ description.substring(0, 30)+"..." + "\n" +
                    "    Weight: "+weight.toString();
        return s;
    }
}
