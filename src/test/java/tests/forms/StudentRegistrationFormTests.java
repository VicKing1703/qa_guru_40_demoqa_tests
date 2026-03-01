package tests.forms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;
import tests.BaseTest;
import tests.TestData;

import static tests.TestData.*;

public class StudentRegistrationFormTests extends BaseTest {
    TestData testData = new TestData();
    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

    @BeforeEach
    void newTestData() {
    }

    @Test
    void successOnlyRequiredFieldsTest() {

        studentRegistrationFormPage.openStudentRegistrationFormPage()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .setGender(testData.sex)
                .typePhoneNumber(testData.userNumber)
                .clickSubmitButton()
                .resultRegistrationModal()
                .shouldAppear()
                .shouldHaveValue("Student Name", testData.firstName + " " + testData.lastName)
                .shouldHaveValue("Gender", testData.sex)
                .shouldHaveValue("Mobile", testData.userNumber);

    }

    @Test
    void successAllFieldTest() {
        studentRegistrationFormPage.openStudentRegistrationFormPage()
                .typeFirstName(testData.firstName)
                .typeLastName(testData.lastName)
                .typeUserEmail(testData.userEmail)
                .setGender(testData.sex)
                .typePhoneNumber(testData.userNumber)
                .setDateOfBirth(testData.dayBirth, testData.monthBirth, testData.yearBirth)
                .typeSubject(testData.subjects)
                .setHobbies(testData.hobbies)
                .uploadPicture(testData.imageName)
                .typeCurrentAddress(testData.currentAddress)
                .setStateAndCity(testData.state, testData.city)
                .clickSubmitButton()
                .resultRegistrationModal()
                .shouldAppear()
                .shouldHaveValue("Student Name", testData.firstName + " " + testData.lastName)
                .shouldHaveValue("Student Email", testData.userEmail)
                .shouldHaveValue("Gender", testData.sex)
                .shouldHaveValue("Mobile", testData.userNumber)
                .shouldHaveValue("Date of Birth", testData.dayBirth + " " + testData.monthBirth +"," + testData.yearBirth)
                .shouldHaveValue("Subjects", testData.subjects)
                .shouldHaveValue("Hobbies", testData.hobbies)
                .shouldHaveValue("Picture", testData.imageName)
                .shouldHaveValue("Address", testData.currentAddress)
                .shouldHaveValue("State and City", testData.state + " " + testData.city);

    }

    @Test
    void errorFieldTest() {
        // без заполнения полей сразу кликнем по кнопке Submit
        studentRegistrationFormPage.openStudentRegistrationFormPage()
                .clickSubmitButton()
                .validErrorFirstName()
                .validErrorLastName()
                .validErrorUserNumber()
                .validErrorGender();
    }

}
