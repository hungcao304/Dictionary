import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline {
    public static void showAllWords() {
        System.out.println("No | English          | Vietnamese");
        for(int i=0; i<Dictionary.listWord.size(); i++) {
            System.out.println(i+1 +"  | " +
                    Dictionary.listWord.get(i).getWord_target()+"          |"+
                    Dictionary.listWord.get(i).word_explain);
        }
    }

    public static void dictionarySearcher() {
        ArrayList<String> list = new ArrayList<>();
        System.out.println("Enter a word you want to search. Enter 0 to get back to main menu.");
        Scanner in = new Scanner(System.in);
        String searchedWord = in.nextLine();
        while (!searchedWord.equals("0")) {
            for (int i = 0; i < Dictionary.listWord.size(); i++) {
                String word = Dictionary.listWord.get(i).getWord_target();
                if (word.contains(searchedWord)) {
                    list.add(word);
                }
            }
            if (list.size() == 0) {
                System.out.println("Word not found");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    System.out.print(list.get(i) + " ");
                }
            }
            System.out.println("\n");
            searchedWord = in.nextLine();
        }
    }

    public static void dictionaryBasic(){
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }

    public static void dictionaryAdvanced() throws IOException {
        DictionaryManagement.insertFromFile( );
        showAllWords( );
        DictionaryManagement.dictionaryLookup();
    }

    public static void main(String[] args) throws IOException {
        DictionaryManagement.insertFromFile( );
        String instruction = "Welcome to The Dictionary. Please tell me how can I help you! \n" +
                "Press 1 to show all words \n" +
                "Press 2 to look up a word \n" +
                "Press 3 to search a word \n" +
                "Press 4 to insert a word \n" +
                "Press 5 to correct a word \n" +
                "Press 6 to remove a word \n" +
                "Press 7 to export your data to file \n" +
                "Press 8 to show instruction \n" +
                "Press 0 to exit.";
        System.out.println(instruction);
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while (!input.equals("0")){
            switch (input){
                case "1":
                    showAllWords();
                    break;
                case "2":
                    DictionaryManagement.dictionaryLookup();
                    break;
                case "3":
                    dictionarySearcher();
                    break;
                case "4":
                    DictionaryManagement.insertFromCommandline();
                    break;
                case "5":
                    DictionaryManagement.updateWord();
                    break;
                case "6":
                    DictionaryManagement.removeWord();
                    break;
                case "7":
                    DictionaryManagement.dictionaryExportToFile();
                    break;
                case "8":
                    System.out.println(instruction);
                    break;
                default:
                    System.out.println("Wrong message!");
                    break;
            }
            System.out.println("Continue your work. Or press 0 to exit.");
            input = in.nextLine();
        }
    }
}
