package org.rog.libraryapp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDtoWithBooks {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private List<BookWithoutAuthorDto> books;
}
