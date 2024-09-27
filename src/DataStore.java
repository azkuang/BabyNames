/**
 * Alex Kuang - Data Storage Class
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class DataStore {
    // Initialize two hashmaps to hold the baby name data
    private Map<Integer, Map<String, List<BabyData>>> mapByYearAndGender = new HashMap<>(); // Maps year to gender and the babydata object
    private Map<String, Map<String, Integer>> mostPopularYearForNameGender = new HashMap<>(); // Maps name to a map of gender and year

    // Instance Variables
    private String TXT = ".txt";
    private int NUMBER_OF_ITEMS_IN_NAMES = 3;

    // // Test code
    // public static void main(String[] args) {
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

    //     dataStore.displayData();
    //     // System.out.println(mostPopularYearForNameGender);
    // }

    // Method to slice file name to only be the year
    private int parseFileNameToYear(String fileName) {
        // Replace all non-digit characters in the string with an empty string
        return Integer.parseInt(fileName.replaceAll("\\D+", ""));
    }

    // Method to check what the most frequent name for gender per year
    private int getFrequencyForNameAndGender(int year, String gender, String name) {
        return mapByYearAndGender
            .getOrDefault(year, new HashMap<>()) // If year doesn't exist, get an empty map as default
            // Retrieve the list of persons for the given gender, or return an empty list if gender is not found
            .getOrDefault(gender, new ArrayList<>()) // If gender doesn't exist, get an empty list as default
            // Stream through the list of persons to process each one
            .stream() 
            // Filter the list to only include persons with the name that matches the provided name
            .filter(p -> p.getName().equals(name)) 
            // Map the filtered results to their corresponding name frequency
            .mapToInt(p -> p.getNameFrequency()) 
            // Retrieve the first occurrence of the name and return its frequency, or return 0 if no match is found
            .findFirst() 
            .orElse(0); // If no matching name is found, return 0
    }


    // Method to add the data into the hashmaps
    private void addBabyData(String name, String gender, int nameFrequency, int year) {
        mapByYearAndGender
            .computeIfAbsent(year, k -> new HashMap<>()) // Checks if the map exists, if not create one
            .computeIfAbsent(gender, k -> new ArrayList<>()) // Check if the array exists, if not create one
            .add(new BabyData(name, gender, nameFrequency, year)); // Add the BabyData object into the mapByYearAndGender map
        
        mostPopularYearForNameGender
            .computeIfAbsent(name, k -> new HashMap<>()) // Checks if the map exists, if not create one
            // Checks the frequency of names, replaces the most frequent name per year
            .merge(gender, year, (prevYear, newYear) -> { 
                int prevYearFrequency = getFrequencyForNameAndGender(prevYear, gender, name);
                return nameFrequency > prevYearFrequency ? newYear : prevYear;
            });
    }

    // Method to sort the names by frequency
    private void sortDataByFrequency() {
        mapByYearAndGender.values().forEach(genderMap ->
                genderMap.values().forEach(personList ->
                        personList.sort(Comparator.comparingInt(p -> -p.getNameFrequency()))
                )
        );
    }

    // Read the files inside the inputted folder, parse the data, and load into maps
    public void loadData(String folderPath) throws FileNotFoundException {
        File folder = new File(folderPath);
        // Only read txt files inside the folder
        File[] files = folder.listFiles((dir, name) -> name.endsWith(TXT));

        if (files != null) {
            System.out.println("Loading baby names...");
            for (File file : files) {
                int year = parseFileNameToYear(file.getName());
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        String[] parts = line.split(",");
                        if (parts.length == NUMBER_OF_ITEMS_IN_NAMES) {
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

        sortDataByFrequency();
    }

    // Retrieve data from maps
    protected List<BabyData> getDataByYearAndGender(int year, String gender) {
        return mapByYearAndGender.getOrDefault(year, new HashMap<>()).getOrDefault(gender, new ArrayList<>());
    }

    protected Integer getMostPopularYearForName(String name, String gender) {
        return mostPopularYearForNameGender.getOrDefault(name, new HashMap<>()).get(gender);
    }

    protected Map<Integer, Map<String, List<BabyData>>> getMapByYearAndGender() {
        return mapByYearAndGender;
    }

    protected Map<String, Map<String, Integer>> getMapMostPopularYearForNameGender() {
        return mostPopularYearForNameGender;
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

    // public void displayData() {
    //     mostPopularYearForNameGender.forEach((name, yearGenderMap) -> {
    //         System.out.println("Name: " + name);
    //         yearGenderMap.forEach((gender, count) -> {
    //             System.out.println("  Gender: " + gender + " | Count: " + count);
    //         });
    //     });
    // }
    
}
