package calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.TextStyle;
import java.util.Locale;


public class Calendar {
    private static final int width = 4;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";



    private YearMonth yearMonth;


    public Calendar(String month){
        this(month, Year.now().toString());
    }

    public Calendar(){
        yearMonth = YearMonth.now();
    }

    public Calendar(String month, String year){
        String dateStr = month + " " + year;

        DateTimeFormatter fullFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern("MMMM yyyy").toFormatter(Locale.ENGLISH);

        DateTimeFormatter shortFormatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern("MMM yyyy").toFormatter(Locale.ENGLISH);

        try{
            yearMonth = YearMonth.parse(dateStr, fullFormatter);
        }catch (Exception e){
            yearMonth = YearMonth.parse(dateStr, shortFormatter);
        }
    }

    public void print(){
        System.out.println();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern("MMMM yyyy").toFormatter(Locale.ENGLISH);
        System.out.printf("%" + (width * 7) + "s", yearMonth.format(formatter));
        System.out.println();
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            String day = DayOfWeek.values()[i].getDisplayName(TextStyle.SHORT, Locale.UK);
            if(i < DayOfWeek.values().length - 2){
                System.out.printf("%" + width + "s", day);
            }else {
                System.out.printf("%" + (width + 9) + "s", ANSI_YELLOW + day + ANSI_RESET);
            }

        }
        System.out.println();

        DayOfWeek currentDayOfWeek = DayOfWeek.of(1);
        LocalDate day = yearMonth.atDay(1);


        while(day.isBefore(yearMonth.atEndOfMonth().plusDays(1))){
            if(currentDayOfWeek.equals(day.getDayOfWeek())){
                if(day.equals(LocalDate.now())){
                    System.out.printf("%" + (width +  9) + "s", ANSI_RED + day.getDayOfMonth() + ANSI_RESET);
                }else {
                    if(currentDayOfWeek.equals(DayOfWeek.SUNDAY) || currentDayOfWeek.equals(DayOfWeek.SATURDAY)){
                        System.out.printf("%" + (width +  9) + "s", ANSI_YELLOW + day.getDayOfMonth() + ANSI_RESET);
                    }else {
                        System.out.printf("%" + width + "s", day.getDayOfMonth());
                    }
                }


                if(currentDayOfWeek.getValue() == 7)
                    System.out.println();
            }else{
                for (int i = 0; i < width; i++) {
                    System.out.print(" ");
                }
                currentDayOfWeek = currentDayOfWeek.plus(1);
                continue;
            }
            currentDayOfWeek = currentDayOfWeek.plus(1);
            day = day.plusDays(1);
        }
        System.out.println();
    }
}
