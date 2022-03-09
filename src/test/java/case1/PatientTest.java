package case1;

import static case1.PatientTest.UserBuilder.builder;
import static case1.Status.ACTIVE;
import static case1.Status.INACTIVE;
import static java.time.LocalDateTime.now;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Note: Tests are named to order various events, e.g. nowThenActiveToThenDeactivated stands for "activeTo is in the
 * future, and deactivationDate is after activeTo"
 */
public class PatientTest {

  private final PatientService underTest = new PatientService();

  @Test
  void blankUserIsActive() {
    // given
    User blankUser = new User();

    // when
    UserDto result = underTest.getUser(blankUser);

    // then
    assertEquals(ACTIVE, result.getStatus(), "blank user must be active");
  }

  @Test
  void nowThenActiveTo_UserIsActive() {
    // given
    User user = builder().activeTo(now().plusDays(10)).build();

    // when
    UserDto result = underTest.getUser(user);

    // then
    assertEquals(ACTIVE, result.getStatus());
  }

  @Test
  void activeToThenNow_UserIsInactive() {
    // given
    User user = builder().activeTo(now().minusDays(10)).build();

    // when
    UserDto result = underTest.getUser(user);

    // then
    assertEquals(INACTIVE, result.getStatus());
  }

  @Test
  void nowThenDeactivation_UserIsActive() {
    // given
    User user = builder().deactivationDate(now().plusDays(10)).build();

    // when
    UserDto result = underTest.getUser(user);

    // then
    assertEquals(ACTIVE, result.getStatus());
  }

  @Test
  void deactivationThenNow_UserIsInactive() {
    // given
    User user = builder().deactivationDate(now().minusDays(10)).build();

    // when
    UserDto result = underTest.getUser(user);

    // then
    assertEquals(INACTIVE, result.getStatus());
  }

  @Test
  void nowThenActiveToThenDeactivation_UserIsActive() {
    // given
    User user = builder()
        .activeTo(now().plusDays(5))
        .deactivationDate(now().plusDays(10)).build();

    // when
    UserDto result = underTest.getUser(user);

    // then
    assertEquals(ACTIVE, result.getStatus());
  }

  @Disabled("Disables until a bug is fixed")
  @Test
  void nowThenDeactivationThenActiveTo_UserIsActive() {
    // given
    User user = builder()
        .deactivationDate(now().plusDays(5))
        .activeTo(now().plusDays(10)).build();

    // when
    UserDto result = underTest.getUser(user);

    // then
    assertEquals(ACTIVE, result.getStatus());
  }

  // helper methods

  static final class UserBuilder {

    private LocalDateTime activeTo;

    private LocalDateTime deactivationDate;

    private UserBuilder() {
    }

    public static UserBuilder builder() {
      return new UserBuilder();
    }

    public UserBuilder activeTo(LocalDateTime activeTo) {
      this.activeTo = activeTo;
      return this;
    }

    public UserBuilder deactivationDate(LocalDateTime deactivationDate) {
      this.deactivationDate = deactivationDate;
      return this;
    }

    public User build() {
      return new User(1L, activeTo, new Details(2L, deactivationDate));
    }
  }

}
