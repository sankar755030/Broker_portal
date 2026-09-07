package utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class UniqueNameGenerator {

    private static final String BASE_NAME = "TestSan";
    private static final int PAD_LENGTH = 4;
    private static final String FILE_PATH = "src/test/resources/testfiles/uniqueNameIndex.txt";

    public static String generateNextName() {
        int currentNumber = readCurrentNumber();
        int nextNumber = currentNumber + 1;
        saveCurrentNumber(nextNumber);
        return BASE_NAME + String.format("%0" + PAD_LENGTH + "d", nextNumber);
    }

    private static int readCurrentNumber() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                Files.writeString(file.toPath(), "18");
                return 18;
            }
            return Integer.parseInt(Files.readString(file.toPath()).trim());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static void saveCurrentNumber(int number) {
        try {
            Files.writeString(Paths.get(FILE_PATH), String.valueOf(number));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String GenerateRandomName0(int length) {
        // Default length is 6 if no length is provided
        if (length <= 0) {
            length = 6;
        }

        // Characters to choose from
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        // Random object
        Random random = new Random();

        // StringBuilder to store the generated name
        StringBuilder name = new StringBuilder(length);

        String finalName="";

        // Generate random name
        for (int i = 0; i < length; i++) {
            // Randomly select a character from the 'chars' string
            finalName = "TestData_" + name.append(chars.charAt(random.nextInt(chars.length()))).toString();
        }

        return finalName;
    }

    private static final String[] ADJECTIVES = {
            "Fast", "Silent", "Bright", "Secure", "Smart", "Quick", "Green", "Blue"
    };

    private static final String[] NOUNS = {
            "River", "Cloud", "Path", "Cache", "Token", "Stream", "Node", "Bridge"
    };

    private static final Random RANDOM = new Random();

    public String GenerateRandomName() {
        String adjective = ADJECTIVES[RANDOM.nextInt(ADJECTIVES.length)];
        String noun = NOUNS[RANDOM.nextInt(NOUNS.length)];
        return "TestData_" + adjective + noun + RANDOM.nextInt(100);
    }
}
