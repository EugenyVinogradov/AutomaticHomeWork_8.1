package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.dataHelper.DataHelper;

import static com.codeborne.selenide.Selenide.*;

public class VerificationPage {
    private SelenideElement verifyCode = $("[data-test-id=code] input");
    private SelenideElement loginButton = $("[data-test-id=action-verify]");
    private SelenideElement emptyCode = $x("//*[text()='Неверно указан код! Попробуйте ещё раз.']");
    private SelenideElement wrongCode = $x("//*[text()='Поле обязательно для заполнения']");
    private SelenideElement blockedUser = $x("//*[text()='Код неверно введен три раза! Пользователь заблокирован.']");

    public DashBoard cardsPage() {
        verifyCode.setValue(DataHelper.getVerificationCode());
        loginButton.click();
        return new DashBoard();
    }

    public VerificationPage enterWrongCode() {
        verifyCode.setValue(DataHelper.wrongVerificationCode());
        loginButton.click();
        return new VerificationPage();
    }

    public void BlockedUser() {
        blockedUser.shouldBe(Condition.visible);
    }
}
