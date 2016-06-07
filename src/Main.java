import calendar.Calendar;

public class Main {


    public static void main(String[] args) {

        Calendar calendar = null;
        if (args.length == 0) {
            calendar = new Calendar();
        } else {
            if (args.length == 1) {
                calendar = new Calendar(args[0]);
            } else {
                calendar = new Calendar(args[0], args[1]);
            }
        }
        calendar.print();
    }
}
