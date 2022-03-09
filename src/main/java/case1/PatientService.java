package case1;

public class PatientService {

  public UserDto getUser(User u) {
    //DocNote: mapping library is omitted
    UserDto dto = new UserDto();
    dto.setActiveTo(u.getActiveTo());

    dto.setStatus(u.getStatus());

    return dto;
  }

}
