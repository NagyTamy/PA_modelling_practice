package com.codecool;

import java.util.*;



public class Main {

    public static void main(String[] args) {
        while (true) {
            try {
                Menu main = new Menu();
                main.main_menu();
            } catch (InputMismatchException e) {
                System.out.println("Please chose a number!");
                continue;
            }
        }
    }
}
