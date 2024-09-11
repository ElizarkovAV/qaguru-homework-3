package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillRegistrationForm() {
        //arrange
        String name = "User";
        String lastName = "Testov";
        String email = "test@email.com";
        String mobile = "+799999999";
        String subject = "Computer";
        String address = "Default city 2 Pushkin's Street";

        //act
        open("/automation-practice-form"); //открыть страницу с формой
        $("#firstName").setValue(name); //ввести имя
        $("#lastName").setValue(lastName); //ввести фамилию
        $("#userEmail").setValue(email); //ввести электронную почту
        $("#genterWrapper .custom-control:nth-child(2) label").click(); //выбрать женский пол
        $("#userNumber").setValue(mobile); //ввести номер телефона

        //выставление даты в календаре react-datepicker
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        js.executeScript("$(\"#dateOfBirthInput\")[0].value='01/06/1992'");

        $("#subjectsInput").setValue(subject).pressEnter(); //выбор в списке значения Computer Science
        $("[for='hobbies-checkbox-2']").click(); //выбор хобби Reading

        $("##uploadPicture").uploadFromClasspath("src/test/resources/pic.png");

        System.out.println("test");


    }
}
