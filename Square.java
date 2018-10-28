package com.xyz;


/**The game consist of different squares which have their own coordinate and character.
 * There can only be one of each coordinate, but characters can be repeated.
 */
public class Square {

    /**The coordinate of the character**/
    private Coordinate coordinate;


    /**Square has one and only one character which can form words with
     * other squares.**/
    private char aChar;


    /**Constructor**/
    Square(Coordinate coordinate, char aChar) {
        this.coordinate = coordinate;
        this.aChar = aChar;
    }


    /**Making getters for the private values.**/
    public Coordinate getCoordinate() {
        return coordinate;
    }

    public char getChar() {
        return aChar;
    }
}
