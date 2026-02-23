package tests.forms;

import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;
import tests.BaseTest;

import java.util.Map;

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
                .shouldHaveValues(Map.of(
                "Student Name", firstName + " " + lastName,
                "Gender", sex,
                "Mobile", userNumber
        ));

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
                .shouldHaveValues(Map.of(
                        "Student Name", firstName + " " + lastName,
                        "Student Email", userEmail,
                        "Gender", sex,
                        "Mobile", userNumber,
                        "Date of Birth", dayBirth + " " + monthBirth +"," + yearBirth,
                        "Subjects", subjects,
                        "Hobbies", hobbies,
                        "Picture", imageName,
                        "Address", currentAddress,
                        "State and City", state + " " + city
                ));

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
