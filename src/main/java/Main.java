import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Employee[] employees = {
                new Employee(1L, "Daniel", "Agar", LocalDate.parse("2018-01-17"), 105_945.50),
                new Employee(2L, "Benard", "Shaw ", LocalDate.parse("2018-10-03"), 197_750.00),
                new Employee(3L, "Carly", "Agar", LocalDate.parse("2014-05-16"), 842_000.75),
                new Employee(4L, "Wesley", "Schneider", LocalDate.parse("2022-06-15"), 74_500.00),
                new Employee(5L, "Anna", "Wiltord", LocalDate.parse("2022-06-15"), 85_750.00),
                new Employee(6L, "Yosef", "Tesfalem", LocalDate.parse("2022-10-31"), 100_000.00),
        };

        new PensionPlan("EX1089", LocalDate.parse("2023-01-17"), 100.0, employees[0]);
        new PensionPlan("SM2307", LocalDate.parse("2019-11-04"), 1_555.50, employees[2]);

        printQuarterlyUpcomingEnrollees(employees);
    }

    private static void printQuarterlyUpcomingEnrollees(Employee[] employees) {
        LocalDate now = LocalDate.now();
        LocalDate lastDayOfQuarter = now.with(now.getMonth().firstMonthOfQuarter().plus(2)).with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("Quarterly Upcomming Enrollees");
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        var o = Arrays.stream(employees)
                .filter(e -> e.getPensionPlan() == null)
                .filter(e -> e.getEmploymentDate().isBefore(lastDayOfQuarter.minusYears(3)))
                .sorted(Comparator
                        .comparing(Employee::getYearlySalary)
                        .reversed()
                        .thenComparing(Employee::getLastName))
                .toArray();

        try {
            System.out.println(mapper.writeValueAsString(o));
        } catch (JsonProcessingException err) {
            err.printStackTrace();
        }
    }
}