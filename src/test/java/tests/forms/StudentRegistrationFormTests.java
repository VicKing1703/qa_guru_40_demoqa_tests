package tests.forms;

import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;
import tests.BaseTest;

import static tests.TestData.*;

public class StudentRegistrationFormTests extends BaseTest {

    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

    @Test
    void successOnlyRequiredFieldsTest() {

        studentRegistrationFormPage.openStudentRegistrationFormPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .setGender(sex)
                .typePhoneNumber(userNumber)
                .clickSubmitButton()
                .resultRegistrationModal()
                .shouldAppear()
                .shouldHaveValue("Student Name", firstName + " " + lastName)
                .shouldHaveValue("Gender", sex)
                .shouldHaveValue("Mobile", userNumber);

    }

    @Test
    void successAllFieldTest() {
        studentRegistrationFormPage.openStudentRegistrationFormPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .setGender(sex)
                .typePhoneNumber(userNumber)
                .setDateOfBirth(dayBirth, monthBirth, yearBirth)
                .typeSubject(subjects)
                .setHobbies(hobbies)
                .uploadPicture(imageName)
                .typeCurrentAddress(currentAddress)
                .setStateAndCity(state, city)
                .clickSubmitButton()
                .resultRegistrationModal()
                .shouldAppear()
                .shouldHaveValue("Student Name", firstName + " " + lastName)
                .shouldHaveValue("Student Email", userEmail)
                .shouldHaveValue("Gender", sex)
                .shouldHaveValue("Mobile", userNumber)
                .shouldHaveValue("Date of Birth", dayBirth + " " + monthBirth +"," + yearBirth)
                .shouldHaveValue("Subjects", subjects)
                .shouldHaveValue("Hobbies", hobbies)
                .shouldHaveValue("Picture", imageName)
                .shouldHaveValue("Address", currentAddress)
                .shouldHaveValue("State and City", state + " " + city);

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
