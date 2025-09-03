/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author hanly
 */
public class SetMenu {
    private String code;
    private String name;
    private Double price;
    private String Ingredients;

    public SetMenu() {
    }

    public SetMenu(String code, String name, Double price, String Ingredients) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.Ingredients = Ingredients;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String Ingredients) {
        this.Ingredients = Ingredients;
    }

    
    
    
    public void display(Date evenDate, int numberOfTable){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.printf("%-18s:%s\n", "Code of Set Menu",this.getCode());
        System.out.printf("%-18s:%s\n", "Set menu name",this.getName());
        System.out.printf("%-18s:%s\n", "Event Date",sdf.format(evenDate));
        System.out.printf("%-18s:%s\n", "Number of tables",numberOfTable);
        System.out.printf("%-18s:%s\n", "Price",String.format("%,.0f Vnd", this.getPrice()));
        System.out.printf("%-18s:\n","Ingredients");
        String ingredients = this.getIngredients().trim();
            if(ingredients.startsWith("\"")&&ingredients.endsWith("\"")){
                ingredients = ingredients.substring(1, ingredients.length()-1).trim();
            }
            String[] strs = ingredients.split("#");
            for (String str : strs) {
                System.out.println(str);
            }
    }  
    
}
