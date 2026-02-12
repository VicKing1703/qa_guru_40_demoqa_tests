package tests.forms;

import org.junit.jupiter.api.Test;
import tests.BaseTest;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class FormPositiveTests extends BaseTest {

    @Test
    void successOnlyRequiredFields() {
        open("/automation-practice-form/");
        $("[data-testid=ClearIcon]").click();
        $("input[data-testid='firstName']").setValue("John");
        $("input[data-testid='lastName']").setValue("Cena");
        $("input[data-testid='email']").setValue("johncena@wwe.com");
        $("input[data-testid='phone']").setValue("8001234567");
        $("input[value='Male']").click();


        $("button[type='submit']").scrollTo().click();

        $(withText("submitting")).scrollTo().equals(byText("Thank you for submitting the form"));

    }

}
