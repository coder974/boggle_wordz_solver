package com.xyz;


import java.util.ArrayList;

/**
 * The game itself is represented as a table which is constructed by squares. Table must be in the form of
 * a large square, which means that the width and the height of the table are the same.
 **/
class Table {

    /**
     * The squares of the table are saved in an array. Note that we can't change the size
     * of the table after one is created.
     **/
    private Square[] squares;


    /**
     * The size of the squares is defined as the size of the table. For example the size of the 3x3 table is 9.
     **/
    private int size;

    /**
     * Constructor
     **/
    Table(Square[] squares, int size) {
        this.squares = squares;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public Square[] getSquares() {
        return squares;
    }

    /**Calculates how many certain characters there are in a table.**/
    private int chars_in_table(char aChar) {
        int counter = 0;
        for (int i = 0; i < this.size; i++) {
            if (this.squares[i].getChar() == aChar) {
                counter++;
            }
        }
        return counter;
    }

    /**Calculates how many certain characters are there in a string.**/
    private int chars_in_string(String string, char aChar) {
        int counter = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == aChar) {
                counter++;
            }
        }
        return counter;
    }

    /**Returns an array of words which can be formed from the characters of the table. We don't
     * take neighboring into account in this function.**/
    ArrayList<String> simple_search(ArrayList<String> strings) {

        /*Boolean value for the word in search*/
        boolean possible_word;

        /*Words that pass this simple search will be added in this array.**/
        ArrayList<String> possible_words = new ArrayList<String>();

        for (String string : strings) {

            /*The expected boolean value before the search of the certain word.**/
            possible_word = true;

            for (int i = 0; i < string.length(); i++)
                if (!(chars_in_string(string, string.charAt(i)) <= chars_in_table(string.toLowerCase().charAt(i)))) {

                    /*If the word can not be formed, we stop the loop and set
                       the boolean value to false.**/
                    possible_word = false;
                    i = string.length() - 1;

                }

            if (possible_word) {
                possible_words.add(string);


            }
        }

        return possible_words;
    }

    /**Finds an array of coordinates for every character in the word and makes a list of
     * those arrays.**/
    private ArrayList<ArrayList<Coordinate>> find_coordinates(String string) {

        ArrayList<ArrayList<Coordinate>> coordinates = new ArrayList<ArrayList<Coordinate>>();

        for (int i = 0; i < string.length(); i++) {
            for (int j = 0; j < this.size; j++) {
                if (string.toLowerCase().charAt(i) == this.squares[j].getChar()) {
                    coordinates.add(new ArrayList<Coordinate>());
                    coordinates.get(i).add(this.squares[j].getCoordinate());
                }
            }
        }
        return coordinates;
    }

    /**Makes a list of all possible paths for the word without taking repeating coordinates or
     * neighboring of the coordinates into account.**/
    private ArrayList<ArrayList<Coordinate>> find_possible_paths(ArrayList<ArrayList<Coordinate>> coordinates) {

        /*New array for the possible paths.**/
        ArrayList<ArrayList<Coordinate>> paths = new ArrayList<ArrayList<Coordinate>>();

        /*The value of all the possible combinations**/
        int mainCounter = 1;

        /*The length of the word**/
        int counter = 0;

        /*This value tells how many different combinations have been set before**/
        int coordinateSize = 1;

        /*Calculating the word length*/
        for (ArrayList<Coordinate> coordinate : coordinates) {
            if (coordinate.size() != 0) {
                counter++;
            }
        }

        /*Calculating the value for combinations.**/
        for (int i = 0; i < counter; i++) {
            mainCounter = mainCounter * coordinates.get(i).size();
        }

        /*Adding empty arrays into the path array.**/
        for (int h = 0; h < mainCounter; h++) {
            paths.add(new ArrayList<Coordinate>());
        }

        for (int k = 0; k < counter; k++) {
            for (int j = 0; j < mainCounter; j++) {
                paths.get(j).add(coordinates.get(k).get((j / coordinateSize) % coordinates.get(k).size()));
            }
            /*Updating the value of current different combinations**/
            coordinateSize = coordinateSize * coordinates.get(k).size();
        }

        return paths;


    }

    /*Checks that none of the coordinates is repeated*/
    private boolean coordinate_is_not_used_multiple_times(ArrayList<Coordinate> coordinates) {

        for (int i = 0; i < coordinates.size() - 1; i++) {
            for (int j = i + 1; j < coordinates.size(); j++) {
                if (coordinates.get(i) == coordinates.get(j)) {
                    return false;
                }
            }

        }
        return true;
    }
    /*Checks that the adjacent coordinates are neighbors.**/
    private boolean areNeighbors(ArrayList<Coordinate> coordinates) {

        for (int i = 0; i < coordinates.size() - 1; i++) {
            if (!coordinates.get(i).isNeighbor(coordinates.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    /*Filters the list of different combinations. At this point we take repeating coordinates
    and neighboring into account.
     */
    private boolean find_explicit_paths(ArrayList<ArrayList<Coordinate>> coordinates) {

        int counter = 0;

        for (ArrayList<Coordinate> coordinate : coordinates) {
            if (coordinate.size() != 0) {
                counter++;
            }
        }
        for (int i = 0; i < counter; i++) {
            if (coordinate_is_not_used_multiple_times(coordinates.get(i)) && areNeighbors(coordinates.get(i))) {
                return true;
            }
        }
        return false;
    }

    /*Returns the final list of possible words*/
    ArrayList<String> true_word_search(ArrayList<String> strings) {

        ArrayList<String> true_words = new ArrayList<String>();

        for (String string : strings) {
            ArrayList<ArrayList<Coordinate>> coordinates = find_coordinates(string);
            ArrayList<ArrayList<Coordinate>> possible_paths = find_possible_paths(coordinates);
            if (find_explicit_paths(possible_paths) && !true_words.contains(string) && (string.length() > 2)) {
                true_words.add(string.toLowerCase());
            }

        }
        return true_words;

    }


}
