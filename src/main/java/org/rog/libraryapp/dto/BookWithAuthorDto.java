package org.rog.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookWithAuthorDto {
    private Long id;
    private String title;
    private AuthorDtoWithoutBooks author;
}
