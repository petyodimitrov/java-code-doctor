package case1;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

  private Long id;

  private LocalDateTime activeTo;

  public Status status;

}
