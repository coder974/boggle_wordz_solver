package com.ylajaaski;


import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;




class StringLengthComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {

        if (o1.length() < o2.length()) {
            return 1;
        }
        if (o1.length() > o2.length()) {
            return -1;
        }
        return 0;
    }
}

public class Main {


    private static Scanner scanner = new Scanner(System.in);



    private static boolean gameStarted = false;

    public static void main(String[] args) {

        game();
        System.out.println("Thank you for trying wordz_solver2");

    }
    public static void game() {

        if (!gameStarted) {


            ArrayList<Square> squares = new ArrayList<Square>();
            int counter = 0;
            System.out.println("Wordz_solver2.0");


            System.out.println("Choose the size of the table!");
            int size = scanner.nextInt();



            System.out.println("Choose the characters of the table!");
            String string = scanner.next();
            for (int i = 0; i < Math.sqrt(size); i++) {
                for (int j = 0; j < Math.sqrt(size); j++) {
                    squares.add(new Square(new Coordinate(j, i), string.charAt(counter)));
                    counter++;

                }
            }
            Square[] tableSquares = squares.toArray(new Square[0]);
            Table table = new Table(tableSquares, size);


            while (!gameStarted) {
                System.out.println("Choose language (f for finnish and e for english");
                char nextChar = scanner.next().charAt(0);
                if (nextChar == 'f') {
                    System.out.println("\n");
                    ArrayList<String> words = readFile("fin_words");
                    gameStarted = true;
                    ArrayList<String> possible_words = table.simple_search(words);
                    ArrayList<String> true_words = table.true_word_search(possible_words);
                    ArrayList<String> sorted = sortArrayList(true_words);

                    for (int i = 0; i < size; i++) {
                        if (i % ((int) Math.sqrt(size)) == 0) {
                            System.out.print("\n");
                        }
                        System.out.print(table.getSquares()[i].getChar() + " ");
                    }
                    System.out.println("\n");

                    int length = 0;
                    for (String aSorted : sorted) {
                        if (length != aSorted.length()) {
                            length = aSorted.length();
                            System.out.println("("+length + " kirjainta)");

                        }
                        System.out.println(aSorted);
                    }

                }
                if (nextChar == 'e') {
                    System.out.println("\n");
                    ArrayList<String> words = readFile("eng_words");
                    gameStarted = true;
                    ArrayList<String> possible_words = table.simple_search(words);
                    ArrayList<String> true_words = table.true_word_search(possible_words);
                    ArrayList<String> sorted = sortArrayList(true_words);

                    for (int i = 0; i < size; i++) {
                        if (i % ((int) Math.sqrt(size)) == 0) {
                            System.out.print("\n");
                        }
                        System.out.print(table.getSquares()[i].getChar() + " ");
                    }
                    System.out.println("\n");


                    int length = 0;
                    for (String aSorted : sorted) {
                        if (length != aSorted.length()) {
                            length = aSorted.length();
                            System.out.println("("+length + " letters)");
                        }
                        System.out.println(aSorted);
                    }
                }
            }

        }

        System.out.println("Want to quit (q) or continue (c)?");
        char someChar = scanner.next().charAt(0);
        if (someChar == 'c') {

            gameStarted = false;
            game();
        }

    }

    private static ArrayList<String> readFile(String filename) {
        ArrayList<String> words = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(new File(filename));
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return words;
    }

    /**Sorting the words by their length*/
    private static ArrayList<String> sortArrayList(ArrayList<String> strings) {

        Collections.sort(strings, new StringLengthComparator());
        return strings;

    }

}
