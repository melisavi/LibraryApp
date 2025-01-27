package org.rog.libraryapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "books")
public class Book {
    @Id
    private Long id;
    @Column (name = "title")
    private String title;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "author_id")
    private Author author;
}
