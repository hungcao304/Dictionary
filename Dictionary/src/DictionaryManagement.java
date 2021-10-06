import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandline(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter an English word and its meaning. Enter 0 to get back to main menu.");
        String english_word = in.nextLine();
        while (!english_word.equals("0")){
            String word_meaning = in.nextLine();
            Word word = new Word(english_word, word_meaning);
            Dictionary.listWord.add(word);
            System.out.println("Your word has been inserted!" + "\n" +
                    "Enter an English word and its meaning. Enter 0 to get back to main menu.");
            english_word = in.nextLine();
        }
    }

    public static void insertFromFile() throws FileNotFoundException {
        File file = new File("C:\\Users\\caohu\\IdeaProjects\\Dictionary\\src\\dictionaries.txt");
        Scanner in = new Scanner(file);
        while (in.hasNextLine()) {
            String english_word = in.next();
            String word_meaning = in.nextLine();
            Word word = new Word(english_word, word_meaning);
            Dictionary.listWord.add(word);
        }
        in.close();
    }

    public static int findWord(String word_lookup){
        int result = -1;
        for (int i = 0; i < Dictionary.listWord.size(); i++) {
            if (Dictionary.listWord.get(i).getWord_target().equals(word_lookup)){
                result = i;
                break;
            }
        }
        return result;
    }

    public static void dictionaryLookup(){
        System.out.println("Enter a word to translate. Enter 0 to get back to main menu.");
        Scanner in = new Scanner(System.in);
        String word_lookup = in.nextLine();
        while (!word_lookup.equals("0")) {
            int index = findWord(word_lookup);
            if(index>=0) {
                System.out.println(Dictionary.listWord.get(index).word_explain);
            } else {
                System.out.println("Word not found");
            }
            word_lookup = in.nextLine();
        }
    }

    public static void updateWord(){
        System.out.println("Enter a word you want to update. Enter 0 to get back to main menu.");
        Scanner in = new Scanner(System.in);
        String wordLookup = in.nextLine();
        while (!wordLookup.equals("0")) {
            int index = findWord(wordLookup);
            if (index >= 0) {
                System.out.println("Enter this word and its meaning:");
                String english_word = in.next();
                in.nextLine(); //bắt ký tự "Enter" khi nhập chuỗi
                String word_meaning = in.nextLine();
                Dictionary.listWord.get(index).setWord_target(english_word);
                Dictionary.listWord.get(index).setWord_explain(word_meaning);
                System.out.println("This word has been updated!");
            } else {
                System.out.println("Word not found");
            }
            wordLookup = in.nextLine();
        }
    }

    public static void removeWord(){
        System.out.println("Enter a word you want to remove. Enter 0 to get back to main menu.");
        Scanner in = new Scanner(System.in);
        String wordLookup = in.nextLine();
        while(!wordLookup.equals("0")) {
            int index = findWord(wordLookup);
            if (index >= 0) {
                Dictionary.listWord.remove(index);
                System.out.println("This word has been removed!");
            } else {
                System.out.println("Word not found");
            }
            wordLookup = in.nextLine();
        }
    }

    public static void dictionaryExportToFile() {
        File file = new File("C:\\Users\\caohu\\IdeaProjects\\Dictionary\\src\\dictionaries.txt");
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < Dictionary.listWord.size(); i++) {
                String temp = Dictionary.listWord.get(i).getWord_target() + "\t" + Dictionary.listWord.get(i).getWord_explain() + "\n";
                fw.write(temp);
            }
            fw.close();
            System.out.println("Your file have been updated!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
