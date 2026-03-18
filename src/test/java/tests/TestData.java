package tests;

import net.datafaker.Faker;

public class TestData {
     Faker faker = new Faker();


    public String firstName = faker.funnyName().name(),
            lastName = faker.name().lastName(),
            userEmail = faker.internet().emailAddress(),
            sex = faker.options().option("Male", "Female", "Other"),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            yearBirth = String.valueOf(faker.number().numberBetween(1900,2100)),
            monthBirth = faker.options().option("January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"),
            dayBirth = String.valueOf(faker.number().numberBetween(1,28)),
            subjects = faker.options().option("Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
                    "Computer Science", "Commerce", "Accounting", "Economics", "Arts",
                    "Social Studies", "History", "Civics"),
            hobbies = faker.options().option("Sports", "Reading", "Music"),
            currentAddress = faker.address().fullAddress(),
            state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = selectCity(state),
            imagePath = faker.options().option("images/hogan.jpg", "images/cena.jpg");

    public  String selectCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> null;
        };
    }
}
