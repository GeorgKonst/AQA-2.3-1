import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.DataGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataGenerator.*;

public class SelenideTest {

    @Test
    void shouldNewFunctional(){
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue(getCity());
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date());
        $("[name='name']").setValue(getName());
        $("[name='phone']").setValue(getPhone());
        $(".checkbox").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText(date())).shouldBe(Condition.text(date()));
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(newDate());
        $(".button").click();
        $(".button_size_s").click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText(newDate())).shouldBe(Condition.text(newDate()));


    }


    @Test
    void shouldValidForm() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue(getCity());
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date());
        $("[name='name']").setValue(getName());
        $("[name='phone']").setValue(getPhone());
        $(".checkbox").click();
        $(".button").click();
        $(byText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText(date())).shouldBe(Condition.text(date()));
    }

    @Test
    void shouldEmptyForm() {
        open("http://localhost:9999/");
        $(".button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotValidCity() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Ясногорск");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date());
        $("[name='name']").setValue(getName());
        $("[name='phone']").setValue(getPhone());
        $(".checkbox").click();
        $(".button").click();
        $(byText("Доставка в выбранный город недоступна")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldEmptyCity() {
        open("http://localhost:9999/");
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date());
        $("[name='name']").setValue(getName());
        $("[name='phone']").setValue(getPhone());
        $(".checkbox").click();
        $(".button").click();
        $(byText("Поле обязательно для заполнения")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotValidDate() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue(getCity());
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(LocalDate.now().plusDays(-1).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        $("[name='name']").setValue(getName());
        $("[name='phone']").setValue(getPhone());
        $(".checkbox").click();
        $(".button").click();
        $(byText("Заказ на выбранную дату невозможен")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotValidTel() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue(getCity());
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date());
        $("[name='name']").setValue(getName());
        $("[name='phone']").setValue("+7910999999");
        $(".checkbox").click();
        $(".button").click();
        $(withText("Телефон указан неверно. Должно быть 11 цифр")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void shouldNotClickCheckbox() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue(getCity());
        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(date());
        $("[name='name']").setValue(getName());
        $("[name='phone']").setValue(getPhone());
        $(".button").click();
        $(".input_invalid").shouldBe(Condition.visible);
    }
}