package com.xyz;


/**This class contains the information of coordinates. Every character in the game will have its own
 coordinate and every word will have its own path of coordinates.
 */
public class Coordinate {

    /**The x-coordinate**/
    private int x;


    /**The y-coordinate **/
    private int y;

    /**Initialization of the constructor**/
    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**x and y coordinates are private values inside the class Coordinate,
     * hence we have to make getters for them.**/
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


   /**When evaluating the path of the word with coordinates, we have to check that adjacent
    * characters are neighbors of each other. Two coordinates are neighboring
    * if they touch straight or diagonally. In other words the distance between them is under sqrt(2).**/
   boolean isNeighbor(Coordinate coordinate) {
       if (Math.abs(this.x-coordinate.x) <= 1 && Math.abs(this.y-coordinate.y) <= 1) {

           /*Coordinates are not neighbors of each other if they are the same coordinate.**/
           return !(this.x == coordinate.x && this.y == coordinate.y);
       }
       return false;
   }
}
