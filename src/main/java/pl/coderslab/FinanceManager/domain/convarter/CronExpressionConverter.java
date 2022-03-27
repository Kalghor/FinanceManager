package pl.coderslab.FinanceManager.domain.convarter;

import java.time.LocalDate;


public class CronExpressionConverter {

    final static String MONTHLY_CRON = "1 0 * day * *";
    final static String YEARLY_CRON = "1 0 * day * *";

    public static String LocalDateTimeToCron(LocalDate date, boolean monthly, LocalDate stopDate) {
        String result = "";
        String day = "";
        String month = "";
        String year = "";
        if (monthly) {
            if (stopDate != null) {
                day = date.getDayOfMonth() + "-" + stopDate.getDayOfMonth();
//                year = date.getYear() + "-" + stopDate.getYear();
            } else {
                day = String.valueOf(date.getDayOfMonth());
//                year = String.valueOf(date.getYear());
            }
            result = MONTHLY_CRON;
            result = result.replace("day", day);
//            result = result.replace("year", year);
        } else {
            if (stopDate != null) {
                day = date.getDayOfMonth() + "-" + stopDate.getDayOfMonth();
//                month = date.getMonthValue() + "-" + stopDate.getMonthValue();
//                year = date.getYear() + "-" + stopDate.getYear();
            } else {
                day = String.valueOf(date.getDayOfMonth());
//                month = String.valueOf(date.getMonthValue());
//                year = String.valueOf(date.getYear());
            }
            result = YEARLY_CRON;
            result = result.replace("day", day);
//            result = result.replace("month", month);
//            result = result.replace("year", year);
        }
        return result;
    }
}