package parameterizedTest.telecomArmenia;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import parameterizedTest.TestBase;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TelecomArmenia extends TestBase {
    @BeforeEach
    void setUp() {
        open("https://www.telecomarmenia.am/en");
    }

    static Stream<Arguments> methodSource() {
        return Stream.of(
                Arguments.of("Eng", List.of("Tariffs Internet Services Online shop Offers Help")),
                Arguments.of("Рус", List.of("Тарифы Интернет Услуги Роуминг Онлайн магазин Предложения Помощь"))
        );
    }

    @MethodSource
    @Tag("Critical")
    @ParameterizedTest(name = "Проверка наличия кнопок из списка {1} на сайте telecomarmenia в локали {0}")
    void methodSource(String locale, List<String> buttons) {
        $(".language-list__list").$(byText(locale)).click();
        $$("//a[contains(@class,'language-list__link--active')]").filter(visible)
                .shouldHave(CollectionCondition.texts(buttons));
    }
}
