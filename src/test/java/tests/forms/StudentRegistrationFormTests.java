package tests.forms;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.StudentRegistrationFormPage;
import tests.BaseTest;

@DisplayName("Тесты для страницы 'Student Registration Form'")
public class StudentRegistrationFormTests extends BaseTest {
    TestData testData = new TestData();
    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

    @ParameterizedTest(name = "Обязательные с полом: {0}")
    @ValueSource(strings = {"Male", "Female", "Other"})
    @Tag("SMOKE")
    @DisplayName("Проверка заполнения и отправки только обязательных полей")
    void successOnlyRequiredFieldsSubmittingTest(String sex) {

        studentRegistrationFormPage
                .openStudentRegistrationFormPage()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .setGender(sex)
                .typePhoneNumber(testData.userNumber)
                .clickSubmitButton()
                .resultRegistrationModal()
                .shouldAppear()
                .shouldHaveValue("Student Name", testData.firstName + " " + testData.lastName)
                .shouldHaveValue("Gender", sex)
                .shouldHaveValue("Mobile", testData.userNumber);

    }

    @ParameterizedTest(name = "Имя {0} и фамилия {1}")
    @CsvFileSource(resources = "/csv/namesForTests.csv")
    @Tag("REGRESS")
    @DisplayName("Проверка заполнения и отправки только обязательных полей c именами на разных языках")
    void successOnlyRequiredFieldsSubmittingTestWithDifferentLanguagesTest(String firstName, String lastName) {

        studentRegistrationFormPage
                .openStudentRegistrationFormPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .setGender(testData.sex)
                .typePhoneNumber(testData.userNumber)
                .clickSubmitButton()
                .resultRegistrationModal()
                .shouldAppear()
                .shouldHaveValue("Student Name", firstName + " " + lastName)
                .shouldHaveValue("Gender", testData.sex)
                .shouldHaveValue("Mobile", testData.userNumber);

    }

    @ParameterizedTest(name = "Проверка с именем {0} и фамилией {1}")
    @CsvSource(value = {
            "A, B",
            "Abraham, Simpson",
            "Rhoshandiatellyneshiaunneveshenk, Koyaanisquatsiuth"
    })
    @Tag("REGRESS")
    @DisplayName("Проверка заполнения и отправки всех полей с разной длинной имени и фамилии")
    void successAllFieldSubmittingTest(String firstName, String lastName) {
        studentRegistrationFormPage
                .openStudentRegistrationFormPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(testData.userEmail)
                .setGender(testData.sex)
                .typePhoneNumber(testData.userNumber)
                .setDateOfBirth(testData.dayBirth, testData.monthBirth, testData.yearBirth)
                .typeSubject(testData.subjects)
                .setHobbies(testData.hobbies)
                .uploadPicture(testData.imagePath)
                .typeCurrentAddress(testData.currentAddress)
                .setStateAndCity(testData.state, testData.city)
                .clickSubmitButton()
                .resultRegistrationModal()
                .shouldAppear()
                .shouldHaveValue("Student Name", firstName + " " + lastName)
                .shouldHaveValue("Student Email", testData.userEmail)
                .shouldHaveValue("Gender", testData.sex)
                .shouldHaveValue("Mobile", testData.userNumber)
                .shouldHaveValue("Date of Birth", testData.dayBirth + " " + testData.monthBirth +"," + testData.yearBirth)
                .shouldHaveValue("Subjects", testData.subjects)
                .shouldHaveValue("Hobbies", testData.hobbies)
                .shouldHaveValue("Picture", testData.imagePath.substring(testData.imagePath.lastIndexOf('/') + 1))
                .shouldHaveValue("Address", testData.currentAddress)
                .shouldHaveValue("State and City", testData.state + " " + testData.city);

    }

    @Test
    @Tag("REGRESS")
    @DisplayName("Проверка подсвечивания ошибкой обязательных полей")
    void highlightedErrorRequiredFieldTest() {
        // без заполнения полей сразу кликнем по кнопке Submit
        studentRegistrationFormPage
                .openStudentRegistrationFormPage()
                .clickSubmitButton()
                .validErrorFirstName()
                .validErrorLastName()
                .validErrorUserNumber()
                .validErrorGender();
    }

    @Test
    @DisplayName("Специально ломаный тест")
    void specialBrokenTest() {
        studentRegistrationFormPage
                .openStudentRegistrationFormPage()
                .clickSubmitButton()
                .resultRegistrationModal().shouldAppear();
    }

}
