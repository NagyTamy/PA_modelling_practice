package com.codecool;

public class Cords {
    private int x;
    private int y;
    UI ui = new UI();


    public Cords(int x, int y){
        try{
            if (x < 1 || x > 1400){
                throw new IllegalArgumentException();
            } else {
                this.x = x;
            }
        } catch (IllegalArgumentException e){
            ui.printMessage("Invalid option");
        }  try{
            if (y < 1 || y > 3000){
                throw new IllegalArgumentException();
            } else {
                this.y = y;
            }
        } catch (IllegalArgumentException e){
            ui.printMessage("Invalid option");
        }
    }


    @Override
    public String toString(){
        return String.format("(%d:%d)", x, y);
    }
}
