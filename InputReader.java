import java.util.HashSet;
import java.util.Scanner;

public class InputReader
{
    private Scanner reader;

    public InputReader()
    {
        reader = new Scanner(System.in);
    }

    public HashSet<String> getInput() 
    {
        System.out.print("> ");
        String inputLine = reader.nextLine().trim().toLowerCase();

      
        if (inputLine.isEmpty()) {
            System.out.println("Input tidak boleh kosong, silakan masukkan pertanyaan atau keluhan.");
            return new HashSet<>(); 
        }

        inputLine = inputLine.replaceAll("[^a-zA-Z0-9 ]", ""); 
        String[] wordArray = inputLine.split("\\s+");  

        HashSet<String> words = new HashSet<>();
        for (String word : wordArray) {
            if (!word.isEmpty()) { 
                words.add(word);
            }
        }
        return words;
    }
}
