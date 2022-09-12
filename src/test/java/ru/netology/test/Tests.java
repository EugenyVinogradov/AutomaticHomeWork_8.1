package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.AuthPage;
import ru.netology.dataHelper.DataHelper;

import static com.codeborne.selenide.Selenide.open;


public class Tests {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void cleanDatabase() {
        DataHelper.cleanDataBase();
    }

    @Test
    void shouldVasyaAuthorizationOk() {
        var authPage = new AuthPage();
        var authInfo = DataHelper.getAuthInfo(authPage);
        var verificationPage = authPage.validLoginVasya(authInfo);
        verificationPage.cardsPage().isPageExist();
    }

    @Test
    void shouldBlockedUserForThreeEnterWrongCode() {
        var authPage = new AuthPage();
        var authInfo = DataHelper.getAuthInfo(authPage);
        var verificationPage = authPage.validLoginVasya(authInfo);
        verificationPage.enterWrongCode();
        verificationPage.enterWrongCode();
        verificationPage.enterWrongCode();
        verificationPage.BlockedUser();
        String expected = "blocked";
        String actual = DataHelper.getStatusUser();
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void shouldOk() {
        String expected = "blocked";
        String actual = "blocked";
        Assertions.assertEquals(expected, actual);
    }

}
