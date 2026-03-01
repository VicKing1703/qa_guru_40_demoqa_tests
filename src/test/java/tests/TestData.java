package tests;

import net.datafaker.Faker;

import static utils.RandomUtils.*;

public class TestData {
     Faker faker = new Faker();


    public String fullName = faker.name().fullName(),
            firstName = faker.funnyName().name(),
            lastName = getRandomString(5),
            userEmail = faker.internet().emailAddress(),
            sex = faker.options().option("Male", "Female", "Other"),
            userNumber = faker.phoneNumber().subscriberNumber(10),
            yearBirth = String.valueOf(faker.number().numberBetween(1900,2100)),
            monthBirth = getRandomMonthOfBirth(),
            dayBirth = String.valueOf(faker.number().numberBetween(1,28)),
            subjects = getRandomSubject(),
            hobbies = faker.options().option("Sports", "Reading", "Music"),
            currentAddress = faker.address().fullAddress(),
            permanentAddress = getRandomString(50),
            state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = selectCity(state),
            imageName = faker.options().option("hogan.jpg", "cena.jpg");

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
