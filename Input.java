import java.util.ArrayList;

public class Input implements Comparable{
    private int id;
    private String word;
    private ArrayList<Integer> duplicateIn  = new ArrayList();

    public Input(int id, String word){
        this.id = id;
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public int hashCode() {
        return word.toLowerCase().hashCode();
    }

    public ArrayList getDuplicateIn() {
        return duplicateIn;
    }

    public void setDuplicateIn(ArrayList duplicateIn) {
        this.duplicateIn = duplicateIn;
    }

    @Override
    public boolean equals(Object obj) {
        Input input = (Input) obj;
        return input.word.toLowerCase().equals(this.word.toLowerCase());
    }

    @Override
    public String toString() {
        return "Input{" +
                "id=" + id +
                ", word='" + word + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.word.toLowerCase().compareTo(((Input) o).word.toLowerCase());
    }
}
