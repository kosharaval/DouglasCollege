package model;

import model.components.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleshipModel {

    private int boardSize;
    private int numShips;
    private int shipLength;
    private int shipsSunk;
    private Ship[] ships;

    public BattleshipModel(int boardSize, int numShips, int shipLength, int shipsSunk, Ship[] ships) {
        this.boardSize = boardSize;
        this.numShips = numShips;
        this.shipLength = shipLength;
        this.shipsSunk = shipsSunk;
        this.ships = ships;
    }

    public int fire(String guess){
        int result = 0;

        for(int i = 0; i < numShips; i++){
            Ship ship = ships[i];
            int index = ship.getLocations().indexOf(guess);

            if(index >= 0 && index < numShips){
                if(ship.getHits().get(index).equals("hit")){
                    result = 1;
                    return result;
                } else if(index >= 0){
                    ship.setHits("hit", index);
                    result = 2;

                    if(isSunk(ship)){
                        result = 3;
                        shipsSunk++;
                    }

                    return result;
                }
            }
        }
        return result;
    }

    private boolean isSunk(Ship ship){
        for(int i = 0; i < shipLength; i++){
            if(!ship.getHits().get(i).equals("hit")){
                return false;
            }
        }
        return true;
    }

    public void generateShipLocations(){
        List<String> locations = new ArrayList<>();
        for(int i = 0; i < numShips; i++){
            do{
                locations = this.generateShip();
            } while (collision(locations));
            ships[i].setLocations(locations);
        }
        System.out.println("Ships Array: ");
        System.out.println(ships[0].getLocations());
        System.out.println(ships[1].getLocations());
        System.out.println(ships[2].getLocations());
    }

    private List<String> generateShip(){
        Random rand = new Random();
        int direction = rand.nextInt(2);

        int row, col;

        if(direction == 1){
            row = rand.nextInt(boardSize);
            col = rand.nextInt(boardSize - shipLength + 1);
        } else{
            row = rand.nextInt(boardSize - shipLength + 1);
            col = rand.nextInt(boardSize);
        }

        List<String> newShipLocations = new ArrayList<>();
        for(int i = 0; i < shipLength; i++){
            if(direction == 1){
                newShipLocations.add(row + "" + (col + i));
            } else{
                newShipLocations.add((row + i) + "" + col);
            }
        }
        return newShipLocations;
    }

    private boolean collision(List<String> locations){
        for(int i = 0; i < numShips; i++){
            Ship ship = ships[i];
            for(int j = 0; j < locations.size(); j++){
                if(ship.getLocations().indexOf(locations.get(j)) >= 0){
                    return true;
                }
            }
        }
        return false;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getNumShips() {
        return numShips;
    }

    public int getShipsSunk() {
        return shipsSunk;
    }
}
