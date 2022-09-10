package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String entered_number="";
        System.out.println("Enter your number(separator dot(.)):");
        Scanner Scanner=new Scanner(System.in);
        entered_number=Scanner.next();
        if(isNumeric(entered_number)){
            System.out.println(binaryToHex(entered_number));
        } else {
            System.out.println("You didn't enter a number");
        }
    }
    static String HexDigits = "0123456789ABCDEF";
    public static boolean isNumeric(String str) {//проверка на число, то что ввел пользователь 
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    static int binaryToDecimal(String s)//из двоичного в десятичный
    {
        int kef=0;
        for (int i=0; i<4; i++)
        {
            kef=kef*2;
            if (s.charAt(i)=='1') kef++;
        }
        return kef;
    }
    static String binaryToHex(String str)//с двоичной в шестнадцатиричную
    {
        String result="";
        boolean point_is=false;
        int index_point=str.indexOf(".");//индекс позиция точки
        if(index_point == -1){//в случае если разделяющий знак отсутствует
            str=divide_by_quartet(str,point_is);
            result=number_to_hex(str);
        }
        else {
            String number_before_point = str.substring(0, index_point);//цифры до запятой
            String number_after_point = str.substring(index_point + 1);//цифры после запятой
            number_before_point=divide_by_quartet(number_before_point,point_is);//разбиваю по 4 цифры , которые стоят до запятой
            point_is=true;
            number_after_point=divide_by_quartet(number_after_point,point_is);///разбиваю по 4 цифры , которые стоят после запятой
            result=number_to_hex(number_before_point)+"."+number_to_hex(number_after_point);//собираем число
        }
        return result;
    }
    static String number_to_hex(String numbers){//преобразование в шестнадцатиричную систему
        String result="";
        int decimal_number=0;
        for (int i = 0; i < numbers.length(); i += 4)//проходимся по квартетам числа
        {
            decimal_number = binaryToDecimal(numbers.substring(i, i + 4));//преобрахование с двоичной в десятичную
            result = result + HexDigits.charAt(decimal_number);// десятичное число равно индексу алфавита HEX
        }
        return result;
    }
    static String divide_by_quartet(String numbers, boolean after_dot){//разбиваем число на квартеты
       int count_to_quartet = numbers.length() % 4;
       if(count_to_quartet != 0) {
           if (after_dot) numbers = numbers + "0".repeat(4 - count_to_quartet);//если после точки, то нули прибавляются справа
           else numbers = "0".repeat(4 - count_to_quartet) + numbers;
       }
        return numbers;
    }
}
//todo can do universal calculate