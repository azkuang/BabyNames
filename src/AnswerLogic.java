import java.util.List;

public class AnswerLogic {
    // Create constructor for DataStore class to be used
    private final DataStore dataStore;

    public AnswerLogic(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    // Test code
    // public static void main(String[] args) {
    //     DataStore dataStore = new DataStore();
    //     AnswerLogic answerLogic = new AnswerLogic(dataStore);
    //     answerLogic.getMostPopularName("M", 1800);
    // }

    // Method to search through maps to find the most popular name based on gender and year
    public String getMostPopularName(String gender, int year) {
        List<BabyData> babyData = dataStore.getDataByYearAndGender(year, gender);
        if (!babyData.isEmpty()) {
            BabyData mostPopular = babyData.get(0);
            return "The name " + mostPopular.name + ", gender " + mostPopular.gender + ", in the year " + year + ", occurred with frequency " + mostPopular.nameFrequency 
                    + ", and rank 1";
        } else {
            return "No data available for the given gender and year.";
        }
    }

    // Method to display rank for a given name, gender, and year
    public String getRankForName(String name, String gender, int year) {
        List<BabyData> babyData = dataStore.getDataByYearAndGender(year, gender);
        int rank = 1;
        for (BabyData babyName : babyData) {
            if (babyName.name.equalsIgnoreCase(name)) {
                return "The name " + babyName.name + ", gender " + babyName.gender + ", occurred with frequency " + babyName.nameFrequency + ", and rank " + rank;
            } 
            rank++;
        }
        return "No data available for the given name, gender and year.";
    }

    // Method to display the year with the most popular name and gender combo
    public String getMostPopularYearforName(String name, String gender) {
        Integer mostPopularYear = dataStore.getMostPopularYearForName(name, gender);
        if (mostPopularYear !=  null) {
            List<BabyData> babyData = dataStore.getDataByYearAndGender(mostPopularYear, gender);
            for (BabyData babyName : babyData) {
                if (babyName.name.equalsIgnoreCase(name)) {
                    return "The name " + name + ", gender " + gender + ", in the year " + mostPopularYear + ", occurred with frequency " + babyName.nameFrequency + ", and rank 1.";
                }
            }
        }
        return "No data available for given name and gender";
    }
}
