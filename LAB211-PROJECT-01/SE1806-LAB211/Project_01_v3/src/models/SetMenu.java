
package models;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SetMenu implements Comparable<SetMenu> {

    private String code;
    private String name;
    private double price;
    private String ingradients;

    public SetMenu() {
    }

    public SetMenu(String code, String name, double price, String ingradients) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ingradients = ingradients;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngradients() {
        return ingradients;
    }

    public void setIngradients(String ingradients) {
        this.ingradients = ingradients;
    }

    @Override
    public int compareTo(SetMenu that) {
        if (this.price < that.price) {
            return -1;
        } else if (this.price > that.price) {
            return 1;
        } else {
            return 0;
        }
    }

    public void display() {
        System.out.format("%-15s:%s\n", "Code", this.getCode());
        System.out.format("%-15s:%s\n", "Name", this.getName());
        System.out.format("%-15s:%s\n", "Price", String.format("%,.0f Vnd", this.getPrice()));
        System.out.println("Ingredients:");
        System.out.println(this.getIngradients());
    }
    public void display(Date date, int quantity) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.format("%-18s:%s\n", "Code", this.getCode());
        System.out.format("%-18s:%s\n", "Name", this.getName());
        System.out.format("%-18s:%s\n", "Event date", sdf.format(date));
        System.out.format("%-18s:%s\n", "Number of tables", quantity);
        System.out.format("%-18s:%s\n", "Price", String.format("%,.0f Vnd", this.getPrice()));
        System.out.println("Ingredients:");
        String ingredients = this.getIngradients().trim();
           if(ingredients.startsWith("\"") && ingredients.endsWith("\"")){
               ingredients = ingredients.substring(1, ingredients.length()-1).trim();
           }
           String [] strs = ingredients.split("#");
            for (String str : strs) {
                System.out.println(str);
            }
         System.out.println("---------------------------------------------------");
    }
}
