/**
 * Alex Kuang
 */

// Object to hold information about the baby names
class BabyData {
    private String name;
    private String gender;
    private int nameFrequency;
    private int year;

    public BabyData(String name, String gender, int nameFrequency, int year) {
        this.name = name;
        this.gender = gender;
        this.nameFrequency = nameFrequency;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getNameFrequency() {
        return nameFrequency;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Gender: " + gender + ", Frequency: " + nameFrequency + ", Year: " + year;
    }
};