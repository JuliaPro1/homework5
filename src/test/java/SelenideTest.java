import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.DragAndDropOptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class SelenideTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void EnterprizeSearch(){
        open("https://github.com/");
        //Переход в Меню -> Solutions -> Enterprize
        $(byTagAndText("button","Solutions")).hover();
        $(byTagAndText("a","Enterprises")).click();
        //Проверка загрузки страницы с заголовком "The AI-powered developer platform."
        $(withTagAndText("h1","The AI-powered")).shouldHave(text("The AI-powered developer platform"));
    }

    @Test
    void dragDropActions(){
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        //Перенос прямоугольника А на место В помощью Selenide.actions()
        actions().moveToElement($("#column-a")).clickAndHold().moveToElement($("#column-b")).release().perform();
        //Проверка, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }

    @Test
    void dragDropElementToElement(){
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("#column-a").shouldHave(text("A"));
        $("#column-b").shouldHave(text("B"));
        //Перенос прямоугольника А на место В помощью команды $(element).dragAndDrop($(to-element))
        $("#column-a").dragAndDropTo($("#column-b"));
        //$("#column-a").dragAndDrop(DragAndDropOptions.to("#column-b"));  => 2й вариант
        //Проверка, что прямоугольники действительно поменялись
        $("#column-a").shouldHave(text("B"));
        $("#column-b").shouldHave(text("A"));
    }
}
