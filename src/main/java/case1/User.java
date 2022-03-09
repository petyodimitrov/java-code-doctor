package case1;

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

}