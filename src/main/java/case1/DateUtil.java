package case1;

import static java.time.LocalDateTime.now;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtil {

  public static boolean hasPassed(LocalDateTime date) {
    if (date == null) {
      return false;
    }
    return date.isBefore(now());
  }

  public static boolean hasDatePassed(LocalDateTime date) {
    if (date == null) {
      return false;
    }
    return date.toLocalDate().isBefore(LocalDate.now());
  }

}
