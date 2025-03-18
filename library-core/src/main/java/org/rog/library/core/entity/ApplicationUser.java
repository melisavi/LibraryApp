package org.rog.library.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "application_users")
public class ApplicationUser {
    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn (name = "id")
    private ApplicationUserAccount applicationUserAccount;
    @Column (name = "first_name")
    private String firstName;
    @Column (name = "last_name")
    private String lastName;
    @Column (name = "middle_name")
    private String middleName;
    @Column (name = "birth_date")
    private LocalDate birthDate;
    @Column (name = "gender")
    private String gender;
}
