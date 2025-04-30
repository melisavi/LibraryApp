package org.rog.library.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookWithoutAuthorDto {
    private Long id;
    private String title;
    private String login;
}
