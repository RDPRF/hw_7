package parameterizedTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BeelineTest extends TestBase {

    @BeforeEach
    void setUp() {
        open("https://moskva.beeline.ru/customers/products/");
    }

    @ValueSource(strings = {"Частным лицам", "Поставщикам и партнёрам", "Офисы и покрытие", "Помощь и поддержка",})
    @Tag("Minor")
    @ParameterizedTest(name = "Проверка наличия элемента {0} на главной странице сайта в хедере")
    void checkElementTest(String element) {
        $("[data-component=site-header]").shouldHave(text(element));
    }

    @Tag("Critical")
    @CsvSource(value = {
            "Apple, iphone,",
            "Samsung, s22,",
            "По вашему запросу «oppa» ничего не найдено, oppa,",
    })
    @ParameterizedTest(name = "Проверка выдачи поиска {1} при введении названия товара {0}")
    void checkOpenPageTest(String item, String foundItem) {
        $("#searchForm").setValue(item);
        $(".swiper-wrapper").shouldHave(text(foundItem));
    }

}


