package projava;

import one.cafebabe.bc4j.BusinessCalendar;
import static one.cafebabe.bc4j.BusinessCalendar.JAPAN;
import java.time.LocalDate;


public class JapaneseHolidays2022 {
    public static void main(String[] args) {

        BusinessCalendar.newBuilder().holiday(JAPAN.PUBLIC_HOLIDAYS).build()
                .getBusinessDaysBetween(LocalDate.of(2022, 1, 1),
                        LocalDate.of(2022, 12, 31))
                .forEach(System.out::println);
    }


    }
