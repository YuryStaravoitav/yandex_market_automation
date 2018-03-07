package by.ystar.pages;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class YandexMainPage {

    @Step
    public YandexMainPage open() {
        Selenide.open("http://yandex.ru");
        return page(YandexMainPage.class);
    }

    @Step
    public MarketPage clickMarketLink() {
        $(By.xpath("//a[@data-id='market']")).click();
        return page(MarketPage.class);
    }
}
