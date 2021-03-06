package com.codecool;

import java.awt.image.DataBufferDouble;
import java.util.*;

import com.codecool.Player.TroopTiers;
import com.codecool.Player.TroopSpec;
import com.codecool.Player.AllegianceTier;

public class UI{


    public void printMenu(ArrayList<String> list){
        for (int i = 0; i < list.size(); i++){
            String menu = list.get(i);
            System.out.printf("%s.  %s \n", i, menu);
        }
    }

    public void printMessage(String message){
        System.out.println("\n" + message + "\n");
    }



    public int getIntInput(){
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }

    //asks for int user input, displays explanation message passed as parameter
    public int getIntInput(String message){
        System.out.println("\n" + message + "\n");
        return getIntInput();
    }


    public String getStringInput(String message){
        System.out.println("\n" + message + "\n");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    public TroopTiers getTiersFromUser(){
        System.out.println("\nAdd troop tier here (valid format e.g.: 7 or t7 or T7: \n");
        TroopTiers tier;
        Scanner scan = new Scanner(System.in);
        String troopTier = scan.nextLine();
        if (troopTier.toLowerCase().equals("7") || troopTier.toLowerCase().equals("t7")){
            tier = TroopTiers.T7;
        } else if (troopTier.toLowerCase().equals("8") || troopTier.toLowerCase().equals("t8")){
            tier = TroopTiers.T8;
        } else if (troopTier.toLowerCase().equals("9") || troopTier.toLowerCase().equals("t9")){
            tier = TroopTiers.T9;
        } else if (troopTier.toLowerCase().equals("10") || troopTier.toLowerCase().equals("t10")){
            tier = TroopTiers.T10;
        }else {
            throw new IllegalArgumentException("\nPlease make sure to use a valid format (e.g.: 7 or t7 or T7)!\n");
        }
        return tier;
    }


    public Player.AllegianceTier getAllegianceTierFromUser(){
        System.out.println("\nAdd you place in allegiance hierarchy here (valid format e.g.: 1 or t1 or T1): \n");
        AllegianceTier tier;
        Scanner scan = new Scanner(System.in);
        String aTier = scan.nextLine();
        if (aTier.toLowerCase().equals("1") || aTier.toLowerCase().equals("t1")){
            tier = AllegianceTier.T1;
        } else if (aTier.toLowerCase().equals("2") || aTier.toLowerCase().equals("t2")){
            tier = AllegianceTier.T2;
        } else if (aTier.toLowerCase().equals("3") || aTier.toLowerCase().equals("t3")){
            tier = AllegianceTier.T3;
        } else if (aTier.toLowerCase().equals("4") || aTier.toLowerCase().equals("t4")){
            tier = AllegianceTier.T4;
        }else {
            throw new IllegalArgumentException("\nPlease make sure to use a valid format (e.g.: 2 or t2 or T2)!\n");
        }
        return tier;
    }

    public TroopSpec getSpecsFromUser(){
        System.out.println("\nAdd main troop specs here (valid format e.g.: i/c/r or inf/cav/ran or infantry/cavalry/range: \n");
        TroopSpec specs;
        Scanner scan = new Scanner(System.in);
        String troopSpec = scan.nextLine();
        if (troopSpec.toLowerCase().equals("i") || troopSpec.toLowerCase().equals("inf") || troopSpec.toLowerCase().equals("infantry")){
            specs = TroopSpec.Infantry;
        } else if (troopSpec.toLowerCase().equals("c") || troopSpec.toLowerCase().equals("cav") || troopSpec.toLowerCase().equals("cavalry")){
            specs = TroopSpec.Cavalry;
        } else if (troopSpec.toLowerCase().equals("r") || troopSpec.toLowerCase().equals("ran") || troopSpec.toLowerCase().equals("range")){
            specs = TroopSpec.Range;
        } else {
             throw new IllegalArgumentException("\nPlease make sure to use a valid format (e.g.: inf/cav/ran)!\n");
        }
        return specs;
    }


    public int getMarchCountFromUser(){
        int marchCount;
        String optionMarchCount = getStringInput("\nDefault march count is 4. Would you like to set your unique march count?\n");
        if (optionMarchCount.toLowerCase().equals("y") || optionMarchCount.toLowerCase().equals("yes")){
            printMessage("Add available march count here:");
            Scanner scan = new Scanner(System.in);
            marchCount = scan.nextInt();
            if (marchCount > 0 && marchCount <= 5){
                return marchCount;
            } else {
                throw new IllegalArgumentException("\nYou march count supposed to be between 1 and 5, both included.\n");
            }
        } else if (optionMarchCount.toLowerCase().equals("n") || optionMarchCount.toLowerCase().equals("no")){
            marchCount = 4;
        } else {
            throw new InputMismatchException();
        }return marchCount;
    }

    public void pressAnyKeyToContinue(){
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch(Exception e) {}
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void printPlayersList(List<Player> list){
        int i = 1;
        for(Player p : list){
            System.out.printf("%d.\t%s", i, p);
            i++;
        }
    }

    public int getMarchSizeFromUser(TroopTiers tier, Player newPlayer){
        int marchSize;
        String optionMarcSize = getStringInput("\nWould you like to set your unique march size?\n");
        if (optionMarcSize.toLowerCase().equals("y") || optionMarcSize.toLowerCase().equals("yes")){
            printMessage("Add current march size here:");
            Scanner scan = new Scanner(System.in);
            marchSize = scan.nextInt();
            if (marchSize > 40000 && marchSize < 150000){
                return marchSize;
            } else {
                throw new IllegalArgumentException("\nYou march size supposed to be between 40k and 150k! \n");
            }
        } else if (optionMarcSize.toLowerCase().equals("n") || optionMarcSize.toLowerCase().equals("no")){
            marchSize = newPlayer.setDefaultMarchSize(tier);
        } else {
            throw new InputMismatchException();
        } return marchSize;

    }


    public int getMarchSizeFromUser(){
        int marchSize = getIntInput("Add new march size below");
        if (marchSize > 40000 && marchSize < 150000){
            return marchSize;
        } else {
            throw new IllegalArgumentException("\nYou march size supposed to be between 40k and 150k!\n");
        }
    }

    public Player createNewPlayer(){
        do {
            try {
                Player player = new Player();
                String playerName = getStringInput("\nAdd the new character's name below: \n");
                TroopTiers tier = getTiersFromUser();
                TroopSpec spec = getSpecsFromUser();
                int marchSize = getMarchSizeFromUser(tier, player);
                int marchCount = getMarchCountFromUser();
                AllegianceTier aTier = getAllegianceTierFromUser();
                return new Player(playerName, tier, spec, marchSize, marchCount, aTier);
            } catch (IllegalArgumentException e){
                printMessage("Please make sure to enter the right format!\n\nTry again...");
            }

        }while (true);

    }

    //asks for user input on the datas to update

    public void updatePlayerDatas(String xmlFilePath, Player player){

    }

    public void updatePlayerDatas(String xmlFilePath) {

        ReadXML updateDatas = new ReadXML();
        List<Player> modifyList = updateDatas.readAllegianceFromFile(xmlFilePath);
        String searchName = getStringInput("Add the name of the player you want to update here:");
        boolean existingPlayer = updateDatas.ifPlayerExist(xmlFilePath, searchName);
        Player player = updateDatas.findPlayerByName(modifyList, searchName);
        if (!existingPlayer) {
            searchName = getStringInput("There is no player by this name, please choose from the following list, or type ADD to add new player!\n" +
                    updateDatas.findPlayersByName(xmlFilePath, searchName));
            if (searchName.toLowerCase().equals("add")) {
                new WriteXML().appendPlayer(xmlFilePath);
            } else if (!(updateDatas.ifPlayerExist(xmlFilePath, searchName))) {
                printMessage("Player by that name doesn't exist redirecting to create new player now...");
                new WriteXML().appendPlayer(xmlFilePath);
            } else {
                player = updateDatas.findPlayerByName(modifyList, searchName);
                existingPlayer = true;
            }
        }

        ArrayList<String> dataList = new ArrayList<>(List.of("Back to players menu", "Update name", "Update tier", "Update troop spec",
                "Update march size", "Update march count", "Update allegiance tier"));

        boolean stillModify = true;
        while (stillModify) {
            try {
                printMenu(dataList);
                int option = getIntInput("Add the number of data you want to modify:");
                if (option == 0) {
                    new Menu().players_menu();
                } else if (option == 1) {
                    String newPlayerName;
                    while (existingPlayer) {
                        newPlayerName = getStringInput("Add new name");
                        existingPlayer = updateDatas.ifPlayerExist(modifyList, newPlayerName);
                        player.setPlayerName(newPlayerName);
                    }
                    try {
                        String next = getStringInput("Would you like to update other data?");
                        if (next.toLowerCase().equals("y")) {
                            continue;
                        } else if (next.toLowerCase().equals("n")) {
                            String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                            new WriteXML().writeAllegianceToFile(allegianceName, modifyList, xmlFilePath);
                            stillModify = false;
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } catch (IllegalArgumentException w){
                        printMessage("Invalid option, try again!");
                    }
                } else if (option == 2) {
                    boolean validTier = false;
                    while (!validTier) {
                        try {
                            TroopTiers newTier = getTiersFromUser();
                            player.setTier(newTier);
                            validTier = true;
                            String next = getStringInput("Would you like to update other data?");
                            if (next.toLowerCase().equals("y")) {
                                continue;
                            } else if (next.toLowerCase().equals("n")) {
                                String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                                new WriteXML().writeAllegianceToFile(allegianceName, modifyList, xmlFilePath);
                                stillModify = false;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            printMessage("Invalid option, try again!");
                        }
                    }
                } else if (option == 3) {
                    boolean validSpec = false;
                    while (!validSpec) {
                        try {
                            TroopSpec newSpec = getSpecsFromUser();
                            player.setSpec(newSpec);
                            validSpec = true;
                            String next = getStringInput("Would you like to update other data?");
                            if (next.toLowerCase().equals("y")) {
                                continue;
                            } else if (next.toLowerCase().equals("n")) {
                                String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                                new WriteXML().writeAllegianceToFile(allegianceName, modifyList, xmlFilePath);
                                stillModify = false;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            printMessage("Invalid option, try again!");
                        }
                    }
                } else if (option == 4) {
                    boolean validMarchSize = false;
                    while (!validMarchSize) {
                        try {
                            int newMarchSize = getMarchSizeFromUser();
                            player.setMarchSize(newMarchSize);
                            validMarchSize = true;
                            String next = getStringInput("Would you like to update other data?");
                            if (next.toLowerCase().equals("y")) {
                                continue;
                            } else if (next.toLowerCase().equals("n")) {
                                String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                                new WriteXML().writeAllegianceToFile(allegianceName, modifyList, xmlFilePath);
                                stillModify = false;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            printMessage("Invalid option, try again!");
                        }
                    }
                } else if (option == 5) {
                    boolean validMarchCount = false;
                    while (!validMarchCount) {
                        try {
                            int newMarchCount = getMarchCountFromUser();
                            player.setMarchCount(newMarchCount);
                            validMarchCount = true;
                            String next = getStringInput("Would you like to update other data?");
                            if (next.toLowerCase().equals("y")) {
                                continue;
                            } else if (next.toLowerCase().equals("n")) {
                                String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                                new WriteXML().writeAllegianceToFile(allegianceName, modifyList, xmlFilePath);
                                stillModify = false;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            printMessage("Invalid option, try again!");
                        }
                    }

                } else if (option == 6) {
                    boolean validTier = false;
                    while (!validTier) {
                        try {
                            AllegianceTier newTier = getAllegianceTierFromUser();
                            player.setAllTier(newTier);
                            validTier = true;
                            String next = getStringInput("Would you like to update other data?");
                            if (next.toLowerCase().equals("y")) {
                                continue;
                            } else if (next.toLowerCase().equals("n")) {
                                String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                                new WriteXML().writeAllegianceToFile(allegianceName, modifyList, xmlFilePath);
                                stillModify = false;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            printMessage("Invalid option, try again!");
                        }
                    }
                } else {
                    throw new IllegalArgumentException();
                }
                //Player(String playerName, TroopTiers tier, TroopSpec spec, int marchSize, int marchCount)
            } catch (IllegalArgumentException e) {
                printMessage("Invalid option, try again!");
            }
        }
    }


    public void updatePlayer(String playerName, List<Player> membersList, String xmlFilePath){
        ArrayList<String> dataList = new ArrayList<>(List.of("Back to players menu", "Update name", "Update tier", "Update troop spec",
                "Update march size", "Update march count"));
        ReadXML updateDatas = new ReadXML();
        Player player= updateDatas.findPlayerByName(membersList, playerName);
        boolean existingPlayer = updateDatas.ifPlayerExist(membersList, playerName);
        boolean stillModify = true;
        while (stillModify) {
            try {
                printMenu(dataList);
                int option = getIntInput("Add the number of data you want to modify:");
                if (option == 0) {
                    new Menu().players_menu();
                } else if (option == 1) {
                    String newPlayerName;
                    while (existingPlayer) {
                        newPlayerName = getStringInput("Add new name");
                        existingPlayer = updateDatas.ifPlayerExist(membersList, newPlayerName);
                        player.setPlayerName(newPlayerName);
                    }
                    try {
                        String next = getStringInput("Would you like to update other data?");
                        if (next.toLowerCase().equals("y")) {
                            continue;
                        } else if (next.toLowerCase().equals("n")) {
                            String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                            new WriteXML().writeAllegianceToFile(allegianceName, membersList, xmlFilePath);
                            stillModify = false;
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } catch (IllegalArgumentException w){
                        printMessage("Invalid option, try again!");
                    }
                } else if (option == 2) {
                    boolean validTier = false;
                    while (!validTier) {
                        try {
                            TroopTiers newTier = getTiersFromUser();
                            player.setTier(newTier);
                            validTier = true;
                            String next = getStringInput("Would you like to update other data?");
                            if (next.toLowerCase().equals("y")) {
                                continue;
                            } else if (next.toLowerCase().equals("n")) {
                                String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                                new WriteXML().writeAllegianceToFile(allegianceName, membersList, xmlFilePath);
                                stillModify = false;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            printMessage("Invalid option, try again!");
                        }
                    }
                } else if (option == 3) {
                    boolean validSpec = false;
                    while (!validSpec) {
                        try {
                            TroopSpec newSpec = getSpecsFromUser();
                            player.setSpec(newSpec);
                            validSpec = true;
                            String next = getStringInput("Would you like to update other data?");
                            if (next.toLowerCase().equals("y")) {
                                continue;
                            } else if (next.toLowerCase().equals("n")) {
                                String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                                new WriteXML().writeAllegianceToFile(allegianceName, membersList, xmlFilePath);
                                stillModify = false;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            printMessage("Invalid option, try again!");
                        }
                    }
                } else if (option == 4) {
                    boolean validMarchSize = false;
                    while (!validMarchSize) {
                        try {
                            int newMarchSize = getMarchSizeFromUser();
                            player.setMarchSize(newMarchSize);
                            validMarchSize = true;
                            String next = getStringInput("Would you like to update other data?");
                            if (next.toLowerCase().equals("y")) {
                                continue;
                            } else if (next.toLowerCase().equals("n")) {
                                String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                                new WriteXML().writeAllegianceToFile(allegianceName, membersList, xmlFilePath);
                                stillModify = false;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            printMessage("Invalid option, try again!");
                        }
                    }
                } else if (option == 5) {
                    boolean validMarchCount = false;
                    while (!validMarchCount) {
                        try {
                            int newMarchCount = getMarchCountFromUser();
                            player.setMarchCount(newMarchCount);
                            validMarchCount = true;
                            String next = getStringInput("Would you like to update other data?");
                            if (next.toLowerCase().equals("y")) {
                                continue;
                            } else if (next.toLowerCase().equals("n")) {
                                String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                                new WriteXML().writeAllegianceToFile(allegianceName, membersList, xmlFilePath);
                                stillModify = false;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            printMessage("Invalid option, try again!");
                        }
                    }

                } else if (option == 6) {
                    boolean validTier = false;
                    while (!validTier) {
                        try {
                            AllegianceTier newTier = getAllegianceTierFromUser();
                            player.setAllTier(newTier);
                            validTier = true;
                            String next = getStringInput("Would you like to update other data?");
                            if (next.toLowerCase().equals("y")) {
                                continue;
                            } else if (next.toLowerCase().equals("n")) {
                                String allegianceName = updateDatas.getAllegianceName(xmlFilePath);
                                new WriteXML().writeAllegianceToFile(allegianceName, membersList, xmlFilePath);
                                stillModify = false;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } catch (IllegalArgumentException e) {
                            printMessage("Invalid option, try again!");
                        }
                    }
                } else {
                    throw new IllegalArgumentException();
                }
                //Player(String playerName, TroopTiers tier, TroopSpec spec, int marchSize, int marchCount)
            } catch (IllegalArgumentException e) {
                printMessage("Invalid option, try again!");
            }
        }
    }

}
