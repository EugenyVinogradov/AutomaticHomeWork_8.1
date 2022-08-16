package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.page.AuthPage;
import ru.netology.dataHelper.DataHelper;
import ru.netology.page.CardsPage;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;


public class Tests {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void cleanDatabase() {
        DataHelper.CleanDataBase();
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

}
