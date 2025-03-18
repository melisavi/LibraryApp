package org.rog.library.core.util;

import org.rog.library.core.entity.Author;
import org.rog.library.core.entity.ApplicationUserAccount;
import org.rog.library.core.entity.Book;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public final class TestDataProvider {
    private TestDataProvider(){

    }

    public static List<Author> buildAuthor(int count) {
        return IntStream.range(1, ++count).mapToObj(i ->
                Author.builder()
                        .firstName("FirstName" + i)
                        .lastName("LastName" + i)
                        .middleName("MiddleName" + i)
                        .birthDate(LocalDate.now())
                        .deathDate(LocalDate.now())
                        .books(Collections.emptyList())
                        .build()
        ).toList();
    }

    public static List<Book> buildBook(int count, Author author) {
        return IntStream.range(1, ++count).mapToObj(i ->
                Book.builder()
                        .title("Title" + i)
                        .author(author)
                        .build()
        ).toList();
    }

    public static ApplicationUserAccount buildApplicationUser() {
        return ApplicationUserAccount.builder()
                        .login("melisavi")
                        .password("rog")
                        .isAccountNonExpired(true)
                        .isAccountNonLocked(true)
                        .isCredentialsNonExpired(true)
                        .isEnabled(true)
                        .build();
    }
}
