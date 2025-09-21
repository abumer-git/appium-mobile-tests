package utils;

import java.util.Random;

public class TestData {

    private static final String[] FIRST_NAMES = {
            "Mike", "John", "Alice", "Emma", "David", "Sophia", "Chris", "Olivia", "James", "Lily"
    };
    private static final String LAST_NAME = "Khan";
    private static final String EMAIL_DOMAIN = "@yopmail.com";
    private static final Random random = new Random();
    private static final String DEFAULT_PIN = "ABC123"; // hardcoded PIN

    public static String getRandomFirstName() {
        int index = random.nextInt(FIRST_NAMES.length);
        return FIRST_NAMES[index];
    }

    public static String getLastName() {
        return LAST_NAME;
    }

    public static String getRandomEmail(String firstName) {
        int number = random.nextInt(90) + 10; // 10-99
        return firstName.toLowerCase() + "." + LAST_NAME.toLowerCase() + number + EMAIL_DOMAIN;
    }

    public static String getRandomUKMobile() {
        int number = 100000000 + random.nextInt(900000000); // 9 digits
        String mobile = "7" + String.format("%09d", number); // ensures exactly 10 digits
        return mobile;
    }


    public static User getRandomUser() {
        String firstName = getRandomFirstName();
        String lastName = getLastName();
        String email = getRandomEmail(firstName);
        String mobile = getRandomUKMobile();
        String pin = DEFAULT_PIN; // always ABC123
        return new User(firstName, lastName, email, mobile, pin);
    }

    public static class User {
        public String firstName;
        public String lastName;
        public String email;
        public String mobile;
        public String pin;

        public User(String firstName, String lastName, String email, String mobile, String pin) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.mobile = mobile;
            this.pin = pin;
        }
    }
}
