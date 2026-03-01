package tests.forms;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;
import tests.BaseTest;
import utils.RandomUtils;

import static tests.TestData.imageName;
import static utils.RandomUtils.*;

public class StudentRegistrationFormTests extends BaseTest {

    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();
    Faker faker = new Faker();

    String firstName;
    String lastName;
    String sex;
    String userNumber;
    String userEmail;
    String dayBirth;
    String monthBirth;
    String yearBirth;
    String subjects;
    String hobbies;
    String currentAddress;
    String state;
    String city;


    @BeforeEach
    void prepareRandomData() {
        firstName = getRandomString(5);
        lastName = getRandomString(7);
        sex = getRandomGender();
        userEmail = getRandomEmail(5);
        userNumber = faker.phoneNumber().subscriberNumber(10);
        dayBirth = String.valueOf(RandomUtils.getRandomInt(1, 30));
        monthBirth = getRandomMonthOfBirth();
        yearBirth = String.valueOf(getRandomInt(1900, 2026));
        subjects = getRandomSubject();
        hobbies = getRandomHobbie();
        currentAddress = getRandomString(100);
        state = getRandomState();
        city = getRandomCity(state);

    }

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
