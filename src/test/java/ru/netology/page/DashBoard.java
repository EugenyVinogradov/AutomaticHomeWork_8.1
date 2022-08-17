package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;


public class DashBoard {
    private SelenideElement heading = $x("//*[contains(text(),'Личный кабинет')]");


    public void isPageExist() {
        heading.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }


}
