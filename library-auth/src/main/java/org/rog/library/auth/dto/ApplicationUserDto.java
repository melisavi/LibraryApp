package org.rog.library.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationUserDto {
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private String gender;
}
