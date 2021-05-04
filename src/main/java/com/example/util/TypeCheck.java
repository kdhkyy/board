package com.example.util;

public class TypeCheck {

   /*
       해당 문자열이 정수인지 아닌지 판단하기 위함
       input  : String
       output : boolean
    */
    public boolean isNumeric(String str){
        boolean res = true;
        for (int i = 0; i< str.length(); i++){
            if(!Character.isDigit(str.charAt(i))) return res = false;
        }
        return res;
    }
};
