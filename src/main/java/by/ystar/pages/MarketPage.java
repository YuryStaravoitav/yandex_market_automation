package by.ystar.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class MarketPage {

    @Step
    public MarketPage selectMainMenuItem(String item) {
        $(By.xpath("//a[contains(@class,'topmenu__link')][text()='" + item + "']")).hover();
        return page(MarketPage.class);
    }

    @Step
    public MarketPage selectSubMenuItem(String subMenuItem) {
        $(By.xpath("//a[contains(@class,'topmenu__subitem')][text()='" + subMenuItem + "']")).click();
        return page(MarketPage.class);
    }

    @Step
    public MarketPage enterPriceBefore(String price) {
        $("#glf-priceto-var").waitUntil(Condition.appear, 20 * 1000).setValue(price).pressEnter();
        return page(MarketPage.class);
    }

    @Step
    public MarketPage enterScreenDiagonal(String diagonal) {
        if (!$("#glf-4925721-from").isDisplayed()) {
            $(By.xpath("//span[@class='title__content'][contains(text(),'Диагональ экрана')]")).click();
        }
        $("#glf-4925721-from").waitUntil(Condition.appear, 20000).setValue(diagonal).pressEnter();
        return page(MarketPage.class);
    }

    @Step
    public MarketPage selectCheckBoxFromOptions(String osValue) {
        $(By.xpath("//label[text()='" + osValue + "']/../span/parent::span")).setSelected(true);
        return page(MarketPage.class);
    }

    @Step
    public MarketPage clickApplyButton() {
        $(".button_action_n-filter-apply").click();
        waitASec(5);
        return page(MarketPage.class);
    }

    @Step
    public MarketPage clickSortByType(String sortType) {
        $(By.xpath("//a[contains(@class,'n-filter-sorter__link')][text()='" + sortType + "']")).click();
        return page(MarketPage.class);
    }

    @Step
    public ProductDetailsPage clickOnItem(String item) {
        while (true) {
            waitASec(4);
            if ($$(By.xpath("//a/h4[contains(text(),'" + item + "')]")).size() > 0) {
                $(By.xpath("//a/h4[contains(text(),'" + item + "')]")).click();
                waitASec(8);
                return page(ProductDetailsPage.class);
            } else {
                if ($$(".button.n-pager__button-next").size() > 0) {
                    $(".n-pager__button-next").click();
                    waitASec(8);
                } else {
                    return page(ProductDetailsPage.class);
                }
            }
        }
    }

    public String getTheFirstElementName() {
        return $$("a h4.title").get(0).getText();
    }

    public int getCountOfElements() {
        return $$(".n-snippet-cell2").size();
    }

    @Attachment
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    private void waitASec(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (Exception e) {
        }
    }
}
