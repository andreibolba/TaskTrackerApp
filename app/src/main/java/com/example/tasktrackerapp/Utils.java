package com.example.tasktrackerapp;

public class Utils {
    public static String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public static String passPatternSpecial="\" !\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~\"";
    public static Boolean isCorrectEmail(String email){
        return email.matches(emailPattern)==true?true:false;
    }

    public static int checkPassword(String pass){
        if(pass.toString().length()<8)
            return 101;
        Boolean isAllRight=false;
        for(int i=0;i<pass.length();i++) {
            if (pass.charAt(i) <= '9' && pass.charAt(i) >= '0')
                isAllRight = true;
        }
        if(isAllRight==false)
            return 102;
        isAllRight=false;
        for(int i=0;i<pass.length();i++) {
            if (pass.charAt(i) <= 'Z' && pass.charAt(i) >= 'A')
                isAllRight = true;
        }
        if(isAllRight==false)
            return 103;
        isAllRight=false;
        for(int i=0;i<pass.length();i++) {
            if (pass.charAt(i) <= 'z' && pass.charAt(i) >= 'a')
                isAllRight = true;
        }
        if(isAllRight==false)
            return 104;
        isAllRight=false;
        for(int i=0;i<pass.length();i++){
            for (int j=0;j<passPatternSpecial.length();j++)
                if(pass.charAt(i)==passPatternSpecial.charAt(j))
                    isAllRight=true;
        }
        if(isAllRight==false)
            return 105;
        return 100;
    }
}
