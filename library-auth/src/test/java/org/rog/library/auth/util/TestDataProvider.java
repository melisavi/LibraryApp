package org.rog.library.auth.util;

import org.rog.library.common.entity.ApplicationUserAccount;

public final class TestDataProvider {
    private TestDataProvider(){

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
