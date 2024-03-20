package exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;




@Entity
@Table(name = "guests")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    // BEGIN
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
    // END

    @CreatedDate
    private LocalDate createdAt;
}
