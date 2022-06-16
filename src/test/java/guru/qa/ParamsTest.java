package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ParamsTest {

    @ValueSource(strings = {"Кофеварка", "Сковорода"})
    @ParameterizedTest(name = "При поиске на Avito по запросу {0} в результате отображается {0}")
    void yaTest(String testData) {
        Selenide.open("https://www.avito.ru/");
        $(".input-input-Zpzc1").val(testData).pressEnter();
        $$(".items-items-kAJAg").find(text(testData)).shouldBe(visible);
    }

    @CsvSource(value = {
            "Кофеварка, Кофеварка Philips, служит верой и правдой и ещё столько же проработает. Продаю за ненадобностью.",
            "Сковорода, Покупала в магазине. Пользовалась 2 раза. Лежит без дела. Есть нюанс — ребёнок «припалил»"
    })
    @ParameterizedTest(name = "При поиске на Avito по запросу {0} в результате отображается {1}")
    void yaTestq(String searchData, String expectedResult) {
        Selenide.open("https://www.avito.ru/");
        $(".input-input-Zpzc1").val(searchData).pressEnter();
        $$(".items-items-kAJAg").find(text(expectedResult)).shouldBe(visible);
    }


    static Stream<Arguments> yaTestw() {
        return Stream.of(
                Arguments.of("Кофеварка", "Рожковая"),
                Arguments.of("Сковорода", "Чугунная")
        );
    }

    @MethodSource
    @ParameterizedTest(name = "При поиске на Avito по запросу {0} в результате отображается {1}")
    void yaTestw(String searchData, String expectedResult) {
        Selenide.open("https://www.avito.ru/");
        $(".input-input-Zpzc1").val(searchData).pressEnter();
        $$(".items-items-kAJAg").shouldHave(CollectionCondition.texts(expectedResult));
    }

    @EnumSource(Utvar.class)
    @ParameterizedTest(name = "При поиске на сайте авито по запросу {0} в результате отображается - {1}")
    void avitoEnumTest(Utvar utvar) {
        Selenide.open("https://www.avito.ru");
        $(".input-input-Zpzc1").val(utvar.desc).pressEnter();
        $(".items-items-kAJAg").find(String.valueOf(text(utvar.desc)));
    }
}