import calendar.Calendar;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Integer month = null;
        Scanner scanner = new Scanner(System.in);

        try{
            month = scanner.nextInt();
        }catch (Exception e){

        }

        Calendar calendar;

        if(month != null){
            calendar = new Calendar(month);
        }else {
            calendar = new Calendar();
        }

        calendar.print();
    }
}
