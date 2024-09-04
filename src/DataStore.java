import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataStore {
    // Initialize two hashmaps to hold the baby name data
    private Map<Integer, Map<String, List<BabyData>>> mapByYearAndGender = new HashMap<>();
    private Map<String, Map<String, Integer>> mostPopularYearForNameGender = new HashMap<>();

    // Test code
    // public void main(String[] args) {
    //     // Create constructor for the DataStore class
    //     DataStore dataStore = new DataStore();
    //     // Define filePath where files are located
    //     String filePath = "names_test";

    //     try {
    //         dataStore.loadData(filePath);
    //         // dataStore.displayData();
    //     } catch (FileNotFoundException e) {
    //         System.out.println("File not found");
    //     }
    //     System.out.println(mapByYearAndGender);
    // }

    // Method to slice file name to only be the year
    private int parseFileNameToYear(String fileName) {
        // Replace all non-digit characters in the string with an empty string
        return Integer.parseInt(fileName.replaceAll("\\D+", ""));
    }

    // Method to check what the most frequent 
    private int getFrequencyForNameAndGender(int year, String gender, String name) {
        return mapByYearAndGender
            .getOrDefault(year, new HashMap<>())
            .getOrDefault(gender, new ArrayList<>())
            .stream()
            .filter(p -> p.name.equals(name))
            .mapToInt(p -> p.nameFrequency)
            .findFirst()
            .orElse(0);
    }

    // Method to add the data into the hashmaps
    private void addBabyData(String name, String gender, int nameFrequency, int year) {
        mapByYearAndGender
            .computeIfAbsent(year, k -> new HashMap<>())
            .computeIfAbsent(gender, k -> new ArrayList<>())
            .add(new BabyData(name, gender, nameFrequency, year));
        
        mostPopularYearForNameGender
            .computeIfAbsent(name, k -> new HashMap<>())
            .merge(gender, year, (prevYear, newYear) -> {
                int prevYearFrequency = getFrequencyForNameAndGender(prevYear, gender, name);
                return nameFrequency > prevYearFrequency ? newYear : prevYear;
            });
    }

    // Read the files inside the inputted folder, parse the data, and load into maps
    public void loadData(String folderPath) throws FileNotFoundException {
        File folder = new File(folderPath);
        // Only read txt files inside the folder
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files != null) {
            for (File file : files) {
                int year = parseFileNameToYear(file.getName());
                System.out.println("Loading baby names...");
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] parts = line.split(",");
                        if (parts.length == 3) {
                            String name = parts[0].trim();
                            String gender = parts[1].trim();
                            int nameFrequency = Integer.parseInt(parts[2].trim());
                            addBabyData(name, gender, nameFrequency, year);
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error reading files");
                }
            }
        }
    }

    public List<BabyData> getDataByYearAndGender(int year, String gender) {
        return mapByYearAndGender.getOrDefault(year, new HashMap<>()).getOrDefault(gender, new ArrayList<>());
    }

    public Integer getMostPopularYearForName(String name, String gender) {
        return mostPopularYearForNameGender.getOrDefault(name, new HashMap<>()).get(gender);
    }

    // Test method to see if data was getting added to the maps correctly
    // public void displayData() {
    //     mapByYearAndGender.forEach((year, genderMap) -> {
    //         System.out.println("Year: " + year);
    //         genderMap.forEach((gender, personList) -> {
    //             System.out.println("  Gender: " + gender);
    //             personList.forEach(System.out::println);
    //         });
    //     });
    // }
}
