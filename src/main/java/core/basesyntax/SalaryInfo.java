package core.basesyntax;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLD = LocalDate.parse(dateFrom, FORMATER);
        LocalDate dateToLD = LocalDate.parse(dateTo, FORMATER);
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        for (String name:names) {
            int resultSalary = 0;
            for (String dataLine:data) {
                String[] splitData = dataLine.split(" ");
                if(splitData[NAME_INDEX].equals(name)) {
                    LocalDate dataLineDate = LocalDate.parse(splitData[DATE_INDEX], FORMATER);
                    if((dataLineDate.isAfter(dateFromLD) || dataLineDate.isEqual(dateFromLD))
                            && (dataLineDate.isBefore(dateToLD) || dataLineDate.isEqual(dateToLD))) {
                                resultSalary += Integer.parseInt(String.valueOf(splitData[HOURS_INDEX])) * Integer.parseInt(String.valueOf(splitData[SALARY_INDEX]));
                    }
                }
            }
            result.append(System.lineSeparator()).append(name + " - " + resultSalary);
        }
        return  result.toString();
    }
}
