import java.util.Comparator;

public class AccordingMonkeys implements Comparator<Monkey> {
    public int compare(Monkey m1, Monkey m2)
    {
        return m1.getNumberMonkey().compareTo(m2.getNumberMonkey());
    }
}