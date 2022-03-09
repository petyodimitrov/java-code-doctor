package case1;

import static case1.DateUtil.hasDatePassed;
import static case1.DateUtil.hasPassed;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private Long id;

  private LocalDateTime activeTo;

  private Details details;

  //DocNote: other important properties

  public Status getStatus() {
    Status status = Status.ACTIVE;
    if (getDetails() != null
        && hasPassed(getDetails().getDeactivationDate())) {
      status = Status.INACTIVE;
    }

    if (hasDatePassed(getActiveTo())) {
      status = Status.INACTIVE;
    }

    return status;
  }

}