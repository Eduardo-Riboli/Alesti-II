import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.Map.Entry;

public class Simulador {
    public static void main(String[] args) {
        String fileName = "src/casos-cohen/0100macacos.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            TreeMap <Monkey, ArrayList<String>> monkeyList = new TreeMap<>(new AccordingMonkeys());
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

                    // Ia utilizar array o[n] para adicionar elementos
                    // Vou usar lista que é melhor o[1]
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(words, staticValue, amount)));

                    Monkey monkey = new Monkey(numberMonkey, oddMonkey, pairMonkey);
                    monkeyList.put(monkey, list);
                }
            }

            transferCoconuts(repetitionText, monkeyList);     

            int maxCoconuts = 0;
            int maxNumberMonkey = 0;

            System.out.println("SIMULADOR 1");

            for(Entry<Monkey, ArrayList<String>> entry : monkeyList.entrySet()){
                if(entry.getValue().size() > maxCoconuts){
                    maxCoconuts = entry.getValue().size();
                    maxNumberMonkey = entry.getKey().numberMonkey;
                }

                System.out.println("The monkey " + entry.getKey().numberMonkey + " has " 
                                   + entry.getValue().size() + " coconuts;");
            }

            System.out.println("THE WINNER IS A MONKEY " + maxNumberMonkey + " WITH " + maxCoconuts + " COCONUTS");

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void addCoconutsMonkey(TreeMap<Monkey, ArrayList<String>> monkeylist, String coconuts, int oddOrPair){
        for (Entry<Monkey, ArrayList<String>> entry : monkeylist.entrySet()) {
            if(entry.getKey().getNumberMonkey() == oddOrPair){
                monkeylist.get(entry.getKey()).add(0, coconuts);
                return;
            }
        }
    }

    public static void transferCoconuts(int repetitionText, TreeMap<Monkey, ArrayList<String>> monkeyList){
        for(int i=0; i < repetitionText; i++){
            for(Entry<Monkey, ArrayList<String>> entry : monkeyList.entrySet()){
                ArrayList<String> listOfCoconuts = entry.getValue();
                Monkey monkey = entry.getKey();

                for (String coconuts : listOfCoconuts){
                    if (Integer.parseInt(coconuts) % 2 == 0){
                        int pair = monkey.pairMonkey;
                        addCoconutsMonkey(monkeyList, coconuts, pair);
                    } else {
                        int odd = monkey.oddMonkey;                         
                        addCoconutsMonkey(monkeyList, coconuts, odd);
                    }
                }
                monkeyList.get(entry.getKey()).clear();
            }
        }  
    }
}
