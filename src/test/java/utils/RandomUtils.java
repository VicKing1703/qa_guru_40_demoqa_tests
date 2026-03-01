package utils;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.String.format;

public class RandomUtils {

    public static String getRandomString(int length) {
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        SecureRandom rnd = new SecureRandom();
        for (int i = 0; i < length; i++)
            result.append(LETTERS.charAt(rnd.nextInt(LETTERS.length())));
        return result.toString();
    }

    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static String getRandomItemFromStringArray(String[] stringArray) {
        int arrayLength = stringArray.length;
        int randomIndex = getRandomInt(0, arrayLength - 1);
        return stringArray[randomIndex];
    }

    public static String getRandomGender() {
        String[] genders = {"Male", "Female", "Other"};
        return getRandomItemFromStringArray(genders);
    }

    public static String getRandomMonthOfBirth() {
        String[] months = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return getRandomItemFromStringArray(months);
    };

    public static String getRandomHobbie() {
        String[] hobbies = {"Sports", "Reading", "Music"};
        return getRandomItemFromStringArray(hobbies);
    };

    public static String getRandomSubject() {
        String[] subject = {"Hindi", "English", "Maths", "Physics", "Chemistry", "Biology",
                "Computer Science", "Commerce", "Accounting", "Economics", "Arts",
                "Social Studies", "History", "Civics"};
        return getRandomItemFromStringArray(subject);
    };

    public static String getRandomEmail(int length) {
        return format("%s@%s.com", getRandomString(length), getRandomString(length));
    }

    private static final Map<String, String[]> stateCityMap;

    static {
        stateCityMap = new HashMap<>();
        stateCityMap.put("NCR", new String[]{"Delhi", "Gurgaon", "Noida"});
        stateCityMap.put("Uttar Pradesh", new String[]{"Agra", "Lucknow", "Merrut"});
        stateCityMap.put("Haryana", new String[]{"Karnal", "Panipat"});
        stateCityMap.put("Rajasthan", new String[]{"Jaipur", "Jaiselmer"});
    }

    // 2. Метод для получения случайного штата
    public static String getRandomState() {
        Set<String> states = stateCityMap.keySet();
        // Превращаем keyset в массив и берем рандомный элемент через ваш существующий метод
        return getRandomItemFromStringArray(states.toArray(new String[0]));
    }

    // 3. Метод для получения случайного города по конкретному штату
    public static String getRandomCity(String state) {
        String[] cities = stateCityMap.get(state);
        if (cities == null) {
            return null; // Или выбросить исключение, если штата нет в списке
        }
        return getRandomItemFromStringArray(cities);
    }
}
