/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tool;

/**
 *
 * @author hanly
 */
public class Validator {
    public static final String CUSTOMER_CODE_REGEX = "^[CGKcgk]\\d{4}$";
    public static final String NAME_REGEX = "^.{2,25}$";
    public static final String PHONE_NUMBER_REGEX = "^(03[2-9]|086|09[6-8]|090|093|089|070|079|077|076|078|081|082|083|084|085|088|091|094|052|056|058|092)\\d{7}$";
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String POSITIVE_INTERGER_REGEX = "^[1-9]\\d*$";
       
    public static boolean isValid(String input, String regex) {
        return input.matches(regex);
    }
}
