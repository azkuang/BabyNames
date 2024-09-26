import java.util.List;

/**
 * Alex Kuang - Object to store most popular letter + name
 */

class PopularLetterAndNames {
    private char mostPopularLetter;
    private List<String> names;

    public PopularLetterAndNames(char mostPopularLetter, List<String> names) {
        this.mostPopularLetter = mostPopularLetter;
        this.names = names;
    }

    public char getMostPopularLetter() {
        return mostPopularLetter;
    }

    public List<String> getNames() {
        return names;
    }
}