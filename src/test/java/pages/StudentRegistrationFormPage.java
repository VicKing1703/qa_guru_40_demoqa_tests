package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultModalComponent;
import pages.components.ValidationComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormPage {

    CalendarComponent calendar = new CalendarComponent();
    ResultModalComponent resultModal = new ResultModalComponent();
    ValidationComponent validation = new ValidationComponent();

    // Elements
    // Imput
    private SelenideElement firstNameImput =  $("#firstName");
    private SelenideElement lastNameImput =  $("#lastName");
    private SelenideElement userEmailImput =  $("#userEmail");
    private SelenideElement phoneNumberImput =  $("#userNumber");
    private SelenideElement currentAddressImput =  $("#currentAddress");
    private SelenideElement subjectsInput = $("#subjectsInput");
    // Container
    private SelenideElement genterContainer =  $("#genterWrapper");
    private SelenideElement hobbiesContainer = $("#hobbiesWrapper");
    private SelenideElement stateSelect = $("#state");
    private SelenideElement citySelect = $("#city");
    // Component
    private SelenideElement calendarClicker = $("#dateOfBirthInput");
    // UploadFile
    private SelenideElement pictureUploader = $("#uploadPicture");
    // Button
    private SelenideElement submitButton = $("#submit");
    private ElementsCollection genderRadios = $$("input[name='gender']");
    private ElementsCollection genderLabels = $("#genterWrapper").$$("label.form-check-label");

    // Actions
    public StudentRegistrationFormPage openStudentRegistrationFormPage() {
      open("/");
        $(byText("Forms")).click();
        $(byText("Practice Form")).click();
        return this;
    };

    public StudentRegistrationFormPage typeFirstName(String value) {
        firstNameImput.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage typeLastName(String value) {
        lastNameImput.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage typeUserEmail(String value) {
        userEmailImput.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage typePhoneNumber(String value) {
        phoneNumberImput.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage typeCurrentAddress(String value) {
        currentAddressImput.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage typeSubject(String value) {
        subjectsInput.setValue(value).pressEnter();
        return this;
    }

    public StudentRegistrationFormPage setGender(String value) {
        genterContainer.$(byText(value)).click();
        return this;
    }

    public StudentRegistrationFormPage setHobbies(String value) {
        hobbiesContainer.$(byText(value)).click();
        return this;
    }

    public StudentRegistrationFormPage uploadPicture(String value) {
        pictureUploader.uploadFromClasspath(value);
        return this;
    }

    public StudentRegistrationFormPage setDateOfBirth(String day, String month, String year) {
        calendarClicker.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public StudentRegistrationFormPage setStateAndCity(String state, String city) {
        stateSelect.click();
        stateSelect.$(byText(state)).click();
        citySelect.click();
        citySelect.$(byText(city)).click();
        return this;
    }

    public StudentRegistrationFormPage clickSubmitButton() {
        submitButton.scrollTo().click();
        return this;
    }

    public ResultModalComponent resultRegistrationModal() {
        return resultModal;
    }

    public StudentRegistrationFormPage validErrorFirstName() {
        validation.shouldHaveRedBorder(firstNameImput);
        return this;
    }

    public StudentRegistrationFormPage validErrorLastName() {
        validation.shouldHaveRedBorder(lastNameImput);
        return this;
    }

    public StudentRegistrationFormPage validErrorUserNumber() {
        validation.shouldHaveRedBorder(phoneNumberImput);
        return this;
    }

    public StudentRegistrationFormPage validErrorGender() {

        // radio кружки
        genderRadios.forEach(validation::shouldHaveRedBorder);

        // подписи Male/Female/Other
        genderLabels.forEach(validation::shouldHaveRedText);

        return this;
    }

}
