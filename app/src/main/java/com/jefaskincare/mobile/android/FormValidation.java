package com.jefaskincare.mobile.android;

public class FormValidation {

    public static boolean required(String value) {
        if (value.trim().length() > 0) {
            return true;
        }
        return false;
    }

    public static boolean validEmail(String email) {
        boolean validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        if (validEmail) {
            return true;
        }
        return false;
    }

    public static boolean validPhone(String phone) {
        if (phone.trim().length() == 1 || phone.trim().length() == 2) {
            return true;
        }
        return false;
    }

    public static boolean validPassword(String password) {
        if (password.trim().length() > 0) {
            return true;
        }
        return false;
    }

    public static boolean validNominal(String nominal){
        if (nominal.trim().length() > 0 ){
            return  true;
        }
        return false;
    }

    public static boolean validText(String text){
        if (text.trim().length() > 0){
            return true;
        }
        return false;
    }

    public static boolean validNoRek(String norek){
        if (norek.trim().length() > 3){
            return true;
        }
        return false;
    }

    public static boolean validPin(String pin){
        if (pin.trim().length() == 6 ){
            return true;
        }
        return false;
    }

    public static boolean validPulsa(String phone) {
        if (phone.trim().length() > 9) {
            return true;
        }
        return false;
    }

}
