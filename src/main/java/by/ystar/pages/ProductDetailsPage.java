package by.ystar.pages;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$$;

public class ProductDetailsPage {

    @Step
    public String getRatingValue() {
        return $$(".rating__value").get(0).getText();
    }
}
