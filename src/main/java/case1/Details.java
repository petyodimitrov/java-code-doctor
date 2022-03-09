package case1;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Details {

  private Long id;

  /**
   * When the user was explicitly deactivated
   */
  private LocalDateTime deactivationDate;

  //DocNote: other important properties

}