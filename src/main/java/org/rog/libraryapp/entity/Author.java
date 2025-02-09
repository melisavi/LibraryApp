package org.rog.libraryapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "authors")
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "middle_name")
    private String middleName;
    @Column (name = "birth_date")
    private LocalDate birthDate;
    @Column (name = "death_date")
    private LocalDate deathDate;
    @OneToMany (mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;
}
