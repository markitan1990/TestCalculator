package TestTask;

import TestTask.interfaces.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestCalculator extends Calculator implements Sum, Calculation, Subtraction, Division, Multiplication {

    public void run() {
        setCondition(inputText()) ;
        System.out.printf("Результат = %s", calculation(stringToArray(getCondition())));
    }

    @Override
    public String calculation(String[] arr) {
        try {
            int valid = validNumetic(arr);
            if (valid == 0) {
                System.out.println("Формат введенных данных не верный, попробуйте снова. \n 1) Числа должны быть введены арабские или римские.\n 2) Числа дожны быть Целые\n 3) Числа должны быть больше 0 но не больше 10");
            } else {
                int result = 0;
                switch (arr[1]) {
                    case "-":
                        try {
                            result = subtraction(convertToAR(arr[0]), convertToAR(arr[2]));
                        } catch (NullPointerException ex) {
                            System.out.println("Результат не может быть отрицательным");
                        }
                        break;
                    case "+":
                        result = sum(convertToAR(arr[0]), convertToAR(arr[2]));
                        break;
                    case "/":
                        result = division(convertToAR(arr[0]), convertToAR(arr[2]));
                        break;
                    case "*":
                        result = multiplication(convertToAR(arr[0]), convertToAR(arr[2]));
                        break;
                    default:
                        System.out.println("Не правильный");
                }
                if (valid == 1) {
                    return  result+"";
                } else {
                    return convertToRI(result);
                }
            }
        } catch (Exception ex) {
            System.out.println("Не верный формат");
        }
return "";
    }

    @Override
    public int validNumetic(String[] arr) throws Exception {
        int a = 0;
        int b = 0;
        try {
            a = Integer.parseInt(arr[0]);
            b = Integer.parseInt(arr[2]);
            if (a % 1 == 0 & b % 1 == 0 & a <= 10 & b <= 10) {
                return 1;
            }
        } catch (NumberFormatException r) {
            try {
                a = Roman.valueOf(arr[0]).toInt();
                b = Roman.valueOf(arr[2]).toInt();
                if (a > 0 & b > 0 & a <= 10 & b <= 10) {
                    return 2;
                }
            } catch (Exception ex) {
                return 0;
            }
        }
        return 0;
    }

    public static String inputText() {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        return str;
    }

    public static String[] stringToArray(String str) {
        Pattern pattern = Pattern.compile("(.*)([-/+*])(.*)");
        Matcher matcher = pattern.matcher(str);
        matcher.find();
        return new String[]{matcher.group(1), matcher.group(2), matcher.group(3)};
    }

    public static int convertToAR(String str) {
        int i = 0;
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            i = Roman.valueOf(str).toInt();
        }
        return i;
    }

    public static String convertToRI(int i) {

        int a = i % 10;
        int b = i - a;
        String s = convertList(b) + convertList(a);

        return s;
    }

    public static String convertList(int i) {
        HashMap<String, Integer> key = new HashMap<>();
        key.put("I", 1);
        key.put("II", 2);
        key.put("III", 3);
        key.put("IV", 4);
        key.put("V", 5);
        key.put("VI", 6);
        key.put("VII", 7);
        key.put("VIII", 8);
        key.put("IX", 9);
        key.put("X", 10);
        key.put("XX", 20);
        key.put("XXX", 30);
        key.put("XL", 40);
        key.put("L", 50);
        key.put("LX", 60);
        key.put("LXX", 70);
        key.put("LXXX", 80);
        key.put("XC", 90);
        key.put("C", 100);
        for (Map.Entry a : key.entrySet()) {
            if (a.getValue().equals(i)) {
                return a.getKey().toString();
            }
        }
        return "";
    }

    @Override
    public int sum(int a, int b) {
        return a + b;
    }

    @Override
    public int division(int a, int b) {
        return a / b;
    }

    @Override
    public int multiplication(int a, int b) {
        return a * b;
    }

    @Override
    public int subtraction(int a, int b) {
        if (a - b < 0) {
            throw new NullPointerException();
        }
        return a - b;
    }
}
