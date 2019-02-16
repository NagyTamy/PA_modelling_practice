package com.codecool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.codecool.UI.clearScreen;

public class Menu {

    private String xmlFilePath = "src/main/java/data/players.xml";

    public void main_menu(){
        ArrayList<String> mainMenu = new ArrayList<>(List.of("Exit", "Members", "Rally Planners - not implemented",
                "Find Seat of Power - not implemented", "Troop training calculator - not implemented",
                "Keep Upgrade Calculator - not implemented"));
        UI main = new UI();
        main.printMenu(mainMenu);
        int option;
        do{
            option = main.getIntInput();
            switch (option) {
                case 0:
                    System.exit(0);
                case 1:
                    clearScreen();
                    players_menu();
                    break;
                case 2:
                    clearScreen();
                    System.out.println("Implement Rally Planners menu here \n \n");
                    main.printMenu(mainMenu);
                    break;
                case 3:
                    clearScreen();
                    System.out.println("Implement SOP CRUD menu here \n \n");
                    main.printMenu(mainMenu);
                    break;
                case 4:
                    clearScreen();
                    System.out.println("Call Troop cost calculator here \n \n");
                    main.printMenu(mainMenu);
                    break;
                case 5:
                    clearScreen();
                    System.out.println("Call Keep upgrade calculator here \n \n");
                    main.printMenu(mainMenu);
                    break;
                default:
                    clearScreen();
                    System.out.println("This is not an option! \n \n");
                    main.printMenu(mainMenu);
                    break;
            }
        } while (true);
    }



    void players_menu(){
        clearScreen();
        ArrayList<String> playersMenu = new ArrayList<>(List.of("Exit", "Show members in alphabetical order",
                "Add a new member", "Add multiple new members", "Modify existing member", "Remove member"));
        UI players = new UI();
        players.printMenu(playersMenu);
        int playersOption;
        do{
            playersOption = players.getIntInput();
            switch (playersOption) {
                case 0:
                    main_menu();
                    break;
                //Show members in alphabetical order
                case 1:
                    List<Player> alphabeticalOrder = new ReadXML().readAllegianceFromFile(xmlFilePath);
                    Collections.sort(alphabeticalOrder);
                    players.printPlayersList(alphabeticalOrder);
                    players.pressAnyKeyToContinue();
                    players.printMenu(playersMenu);
                    break;
                //Add new member
                case 2:
                    new WriteXML().appendPlayer(xmlFilePath);
                    players.pressAnyKeyToContinue();
                    players.printMenu(playersMenu);
                    break;
                //Add multiple new members
                case 3:
                    new WriteXML().appendMultiplePlayer(xmlFilePath);
                    players.pressAnyKeyToContinue();
                    players.printMenu(playersMenu);
                    break;
                //Modify existing member
                case 4:
                    players.updatePlayerDatas(xmlFilePath);
                    players.pressAnyKeyToContinue();
                    players.printMenu(playersMenu);
                    break;
                //Remove member
                case 5:
                    try {
                        new WriteXML().removePlayerByName(xmlFilePath);
                    } catch (IllegalArgumentException e){
                        players.printMessage("Player does not exist!");
                        break;
                    }
                    players.pressAnyKeyToContinue();
                    players.printMenu(playersMenu);
                    break;
                default:
                    clearScreen();
                    System.out.println("This is not an option! \n \n");
                    players.printMenu(playersMenu);
                    break;
            }
        } while (true);
    }
}
