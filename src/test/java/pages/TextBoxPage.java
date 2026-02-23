package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class TextBoxPage {

    // Elements
    private SelenideElement fullNameImput = $("#userName");
    private SelenideElement emailImput = $("#userEmail");
    private SelenideElement currentAddressImput = $("#currentAddress");
    private SelenideElement permanentAddressImput = $("#permanentAddress");
    private SelenideElement submitButton = $("#submit");


    // Actions
    public TextBoxPage typeFirstName(String value) {
        fullNameImput.setValue(value);
        return this;
    }

        public TextBoxPage typeEmail(String value) {
            emailImput.setValue(value);
        return this;
    }

        public TextBoxPage typeCurrentAddress(String value) {
            currentAddressImput.setValue(value);
        return this;
    }

        public TextBoxPage typePermanentAddress(String value) {
            permanentAddressImput.setValue(value);
        return this;
    }

        public TextBoxPage clickSubmitButton(String value) {
            submitButton.click();
        return this;
    }


}
