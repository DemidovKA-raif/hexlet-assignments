package exercise.dto;

// BEGIN

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GuestCreateDTO {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Email(message = "Email is not valid")
    private String email;

    @Pattern(regexp = "\\+\\d{11,13}", message = "Phone number must start with '+' and contain from 11 to 13 digits")
    private String phoneNumber;

    @Pattern(regexp = "\\d{4}", message = "Club card number must consist of exactly 4 digits")
    private String clubCard;

    @Future(message = "Card is expired")
    private LocalDate cardValidUntil;
}
// END
