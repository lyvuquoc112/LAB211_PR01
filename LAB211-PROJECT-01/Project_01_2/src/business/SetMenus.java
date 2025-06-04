/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import model.SetMenu;

/**
 *
 * @author hanly
 */
public class SetMenus extends TreeMap<String,SetMenu > implements Workable<SetMenu, String>{
    private String pathFile;

    public SetMenus(String pathFile) {
        try {
            this.pathFile = pathFile;
            this.readFormFile();
        } catch (Exception e) {
            System.out.println("Cannot read from file");
        }
        
    }
    public void readFormFile() throws IOException{
        FileInputStream fis = null;
        try {
            File f = new File(pathFile);
            if(!f.exists()){
                System.out.println("Cannot read data from feastMenu.csv. Please check it");
            }
            fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line ="";
            while((line = br.readLine())!=null){
                SetMenu sm = dataToObeject(line);
                if(sm!=null){
                    this.put(sm.getCode(), sm);
                }
            }isr.close();
        } catch (Exception e) {
        } finally {
            fis.close();
        }
    }

    private SetMenu dataToObeject(String line) {
        SetMenu sm = null;
        String[] strs = line.split(",");
        if(strs.length == 4){
            try {
                String code = strs[0];
                String name = strs[1];
                double price = Double.parseDouble(strs[2]);
                String ingredients = strs[3];
                sm = new SetMenu(code, name, price, ingredients);
            } catch (Exception e) {
            }    
        }
        return sm;
   }

    @Override
    public void addNew(SetMenu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void upDate(SetMenu t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void showAll() {
        System.out.println("---------------------------------------------------");
        System.out.println("List of Set Menus for ordering party");
        System.out.println("---------------------------------------------------");
        List<SetMenu> list = new ArrayList<>(this.values());
        Collections.sort(list,new Comparator<SetMenu>() {
            @Override
            public int compare(SetMenu o1, SetMenu o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });
        for (SetMenu setMenu : list) {
            System.out.printf("%-18s:%s\n", "Code", setMenu.getCode());
            System.out.printf("%-18s:%s\n", "Name", setMenu.getName());
            System.out.printf("%-18s:%s\n", "Price", String.format("%,.0f Vnd", setMenu.getPrice()));
            System.out.printf("%-18s:\n","Ingredients");
            String ingredients = setMenu.getIngredients().trim();
            if(ingredients.startsWith("\"")&&ingredients.endsWith("\"")){
                ingredients = ingredients.substring(1, ingredients.length()-1).trim();
            }
            String[] strs = ingredients.split("#");
            for (String str : strs) {
                System.out.println(str);
            }
           System.out.println("---------------------------------------------------");
        }
    }

    @Override
    public SetMenu searchById(String k) {
        return this.get(k);
    }
    
}
