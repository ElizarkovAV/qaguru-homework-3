package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
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
        String mobile = "7999999999";
        String subject = "Computer";
        String address = "Default city 2 Pushkin's Street";
        String state = "Haryana";
        String city = "Karnal";
        String gender = "Female";
        String hobby = "Reading";
        String date = "29 September,1992";
        String picName = "pic.jpg";

        //act
        open("/automation-practice-form"); //открыть страницу с формой
        $("#firstName").setValue(name); //ввести имя
        $("#lastName").setValue(lastName); //ввести фамилию
        $("#userEmail").setValue(email); //ввести электронную почту
        $(byText(gender)).click(); //выбрать женский пол
        $("#userNumber").setValue(mobile); //ввести номер телефона

        //выставление даты в календаре react-datepicker
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").find("option[value='9']").click();
        $(".react-datepicker__year-select").find("option[value='1992']").click();
        $(".react-datepicker__week").find(".react-datepicker__day--029").click();

        $("#subjectsInput").setValue(subject).pressEnter(); //выбор в списке значения Computer Science
        $(byText(hobby)).click(); //выбор хобби Reading
        $("#uploadPicture").uploadFromClasspath("images/pic.jpg"); //загрузка изображения
        $("#currentAddress").setValue(address); //ввод адреса

        //выбор state и city
        $("#state").scrollTo(); //скролл страницы до инпута

        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click(); //выбор state
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click(); //выбор city

        $("#submit").click(); //нажать submit

        //asserts
        $(".table-responsive").shouldHave(text(name + " " + lastName));
        $(".table-responsive").shouldHave(text(email));
        $(".table-responsive").shouldHave(text(gender));
        $(".table-responsive").shouldHave(text(mobile));
        $(".table-responsive").shouldHave(text(date));
        $(".table-responsive").shouldHave(text(subject));
        $(".table-responsive").shouldHave(text(hobby));
        $(".table-responsive").shouldHave(text(picName));
        $(".table-responsive").shouldHave(text(address));
        $(".table-responsive").shouldHave(text(state + " " + city));

    }
}
