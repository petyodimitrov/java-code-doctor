package case1;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientService {

  public UserDto getUser(User u) {
    //DocNote: mapping library is omitted
    UserDto dto = new UserDto();
    dto.setActiveTo(u.getActiveTo());

    setUserStatus(dto, u);

    return dto;
  }

  private void setUserStatus(UserDto dto, User u) {
    if (u.getDetails() != null && u.getDetails().getDeactivationDate() != null) {
      if (dto.getActiveTo() == null) {
        if (u.getDetails().getDeactivationDate().compareTo(LocalDateTime.now()) < 0) {
          dto.setStatus(Status.INACTIVE);
          return;
        }
      } else {
        if (u.getDetails().getDeactivationDate().compareTo(dto.getActiveTo()) < 0) {
          dto.setStatus(Status.INACTIVE);
          return;
        }
      }
    }

    if (dto.getActiveTo() == null) {
      dto.setStatus(Status.ACTIVE);
      return;
    }
    if (dto.getActiveTo().toLocalDate().compareTo(LocalDate.now()) >= 0) {
      dto.setStatus(Status.ACTIVE);
    } else {
      dto.setStatus(Status.INACTIVE);
    }
  }

}
