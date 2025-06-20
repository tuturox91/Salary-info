package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String SPLITTER = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKED_HOURS_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        int income = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            for (String dataStr : data) {
                String[] splittedString = dataStr.split(SPLITTER);
                if (splittedString[NAME_INDEX].equals(name)) {
                    LocalDate workerLocalDate =
                            LocalDate.parse(splittedString[DATE_INDEX], FORMATTER);
                    if (!workerLocalDate.isBefore(localDateFrom)
                            && !workerLocalDate.isAfter(localDateTo)) {
                        income += Integer.parseInt(splittedString[SALARY_INDEX])
                                * Integer.parseInt(splittedString[WORKED_HOURS_INDEX]);

                    }
                }
            }
            sb.append(System.lineSeparator()).append(name).append(" - ").append(income);
            income = 0;
        }
        return sb.toString();
    }
}
