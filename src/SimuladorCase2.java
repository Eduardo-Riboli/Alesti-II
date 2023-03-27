import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class SimuladorCase2 {
    public static void main(String[] args) {
        String fileName = "src/casos-cohen/0400macacos.txt";
        ArrayList<Monkey2> monkeyList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int repetitionText = 0;
            int staticValue = 11; 

            while ((line = br.readLine()) != null) {
                String[] words = line.split(" "); 

                if (words.length == 3)
                    repetitionText = Integer.parseInt(words[1]);
                else {
                    int numberMonkey = Integer.parseInt(words[1]);
                    int pairMonkey = Integer.parseInt(words[4]);
                    int oddMonkey = Integer.parseInt(words[7]);
                    int amount = Integer.parseInt(words[9]) + staticValue;

                    ArrayList<String> list = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(words, staticValue, amount)));

                    Monkey2 monkey = new Monkey2(numberMonkey, oddMonkey, pairMonkey, list);
                    monkeyList.add(monkey);
                }
            }

            transferCoconuts(repetitionText, monkeyList);     

            int maxCoconuts = 0;
            int maxNumberMonkey = 0;

            System.out.println("SIMULADOR 2");
            for(Monkey2 monkey : monkeyList){
                if(monkey.listMonkey.size() > maxCoconuts){
                    maxCoconuts = monkey.listMonkey.size();
                    maxNumberMonkey = monkey.numberMonkey;
                }

                System.out.println("The monkey " + monkey.numberMonkey + " has " 
                                   + monkey.listMonkey.size() + " coconuts;");
            }

            System.out.println("THE WINNER IS A MONKEY " + maxNumberMonkey + " WITH " + maxCoconuts + " COCONUTS");

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void addCoconutsMonkey(ArrayList<Monkey2> monkeyList, String coconuts, int oddOrPair){
        for (Monkey2 monkey : monkeyList) {
            if(monkey.getNumberMonkey() == oddOrPair){
                monkey.listMonkey.add(coconuts);
                return;
            }
        }
    }

    public static void transferCoconuts(int repetitionText, ArrayList<Monkey2> monkeyList){
        for(int i=0; i < repetitionText; i++){
            for(Monkey2 monkey : monkeyList){

                for (String coconuts : monkey.listMonkey){
                    if (Integer.parseInt(coconuts) % 2 == 0){
                        int pair = monkey.pairMonkey;
                        addCoconutsMonkey(monkeyList, coconuts, pair);
                    } else {
                        int odd = monkey.oddMonkey;                         
                        addCoconutsMonkey(monkeyList, coconuts, odd);
                    }
                }
                monkey.listMonkey.clear();
            }
        }  
    }
}
