// Object to hold information about the baby names
class BabyData {
    String name;
    String gender;
    int nameFrequency;
    int year;

    public BabyData(String name, String gender, int nameFrequency, int year) {
        this.name = name;
        this.gender = gender;
        this.nameFrequency = nameFrequency;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Gender: " + gender + ", Frequency: " + nameFrequency + ", Year: " + year;
    }
};