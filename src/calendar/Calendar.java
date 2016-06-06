package calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;


public class Calendar {



    private YearMonth yearMonth;


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


    public Calendar(int monthNumber){
        this.setYearMonth(monthNumber);
    }

    public Calendar(){
        yearMonth = YearMonth.now();
    }

    public void print(){
        System.out.printf("  Пн ");
        System.out.printf(" Вт ");
        System.out.printf(" Ср ");
        System.out.printf(" Чт ");
        System.out.printf(" Пт ");
        System.out.printf(ANSI_YELLOW + " Сб " + ANSI_RESET);
        System.out.printf(ANSI_YELLOW + " Нд " + ANSI_RESET);
        System.out.println();

        DayOfWeek currentDayOfWeek = DayOfWeek.MONDAY;
        LocalDate day = yearMonth.atDay(1);


        while(day.isBefore(yearMonth.atEndOfMonth().plusDays(1))){
            if(currentDayOfWeek.equals(day.getDayOfWeek())){
                if(day.equals(LocalDate.now())){
                    System.out.printf("%13s", ANSI_RED + day.getDayOfMonth() + ANSI_RESET);
                }else {
                    if(currentDayOfWeek.equals(DayOfWeek.SUNDAY) || currentDayOfWeek.equals(DayOfWeek.SATURDAY)){
                        System.out.printf("%13s", ANSI_YELLOW + day.getDayOfMonth() + ANSI_RESET);
                    }else {
                        System.out.printf("%4s", day.getDayOfMonth());
                    }
                }


                if(currentDayOfWeek.equals(DayOfWeek.SUNDAY))
                    System.out.println();
            }else{
                System.out.print("    ");
                currentDayOfWeek = currentDayOfWeek.plus(1);
                continue;
            }
            currentDayOfWeek = currentDayOfWeek.plus(1);
            day = day.plusDays(1);
        }
        System.out.println();
    }

    public int getYearMonth() {
        return yearMonth.getMonthValue();
    }

    public void setYearMonth(int monthNumber) {

        if(monthNumber > 0 && monthNumber < 13){
            yearMonth = Year.now().atMonth(monthNumber);
        }else {
            throw new IllegalArgumentException("Bad variable: monthNumber = " + monthNumber + ". It may be in interval 1..12.");
        }

    }
}
