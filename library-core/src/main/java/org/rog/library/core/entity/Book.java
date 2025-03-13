package org.rog.library.core.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table (name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (name = "title")
    private String title;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "author_id")
    private Author author;
}
