package by.ystar;

import by.ystar.pages.MarketPage;
import by.ystar.pages.ProductDetailsPage;
import by.ystar.pages.YandexMainPage;
import com.automation.remarks.junit.VideoRule;
import com.automation.remarks.junit5.Video;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class YandexMarketTest {

    @Rule
    public VideoRule videoRule = new VideoRule();

    @Rule
    public ScreenshotTestRule rule= new ScreenshotTestRule();

    private final int COUNT_ELEMENTS_ON_THE_PAGE = 48;

    @Test
    @Video(name ="selectYandexMarket")
    public void selectYandexMarketTest() {
        // 1. Open yandex.ru.
        YandexMainPage yandexMainPage = new YandexMainPage().open();
        //  2. В разделе маркет выбрать Сотовые телефоны.
        MarketPage marketPage = yandexMainPage.clickMarketLink();
        marketPage.selectMainMenuItem("Электроника").selectSubMenuItem("Мобильные телефоны");
        //  3. Зайти в расширенный поиск.
        //  4. Задать параметры поиска: до 45000 рублей, android
        marketPage.enterPriceBefore("45000").selectCheckBoxFromOptions("Android");
        //  5. Выбрать не менее 2 любых производителей, среди популярных.
        marketPage.selectCheckBoxFromOptions("Sony").selectCheckBoxFromOptions("Samsung");
        //  6. Нажать кнопку показать подходящие.
        marketPage.clickApplyButton();
        //  7. Проверить, что элементов на странице 48.
        Assert.assertEquals(COUNT_ELEMENTS_ON_THE_PAGE, marketPage.getCountOfElements());
        //  8. Запомнить первый элемент в списке.
        String firstElement = marketPage.getTheFirstElementName();
        //  9. Изменить Сортировку на другую (популярность или новизна).
        marketPage.clickSortByType("по новизне");
        //  10. Найти и открыть карточку товара, по имени запомненного объекта.
        ProductDetailsPage productDetailsPage = marketPage.clickOnItem(firstElement);
    }
}
