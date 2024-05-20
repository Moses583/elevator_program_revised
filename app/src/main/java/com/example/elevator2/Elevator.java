package com.example.elevator2;


public class Elevator {
    public int floors;

    public Elevator(int floors) {
        this.floors = floors;
    }

    public int pressButton(char floor) {
        switch (floor){
            case 'G':
                return 0;
            case 'A':
                return 1;
            case 'B':
                return 2;
            case 'C':
                return 3;
            default:
                return 4;
        }
    }


}
