import java.util.ArrayList;

public class Monkey2{
    int numberMonkey;
    int oddMonkey;
    int pairMonkey;
    ArrayList<String> listMonkey = new ArrayList<>();

    public Monkey2 (int numberMonkey, int oddMonkey, int pairMonkey, ArrayList<String> list){
        this.numberMonkey = numberMonkey;
        this.oddMonkey = oddMonkey;
        this.pairMonkey = pairMonkey;
        this.listMonkey = list;
    }

    public Integer getNumberMonkey(){
        return this.numberMonkey;
    }
}




