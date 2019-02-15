package com.codecool;

import java.awt.image.DataBufferDouble;
import java.util.*;

import com.codecool.Player.TroopTiers;
import com.codecool.Player.TroopSpec;

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

    public int getMarcSizeFromUser(TroopTiers tier, Player newPlayer){
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

    public Player createNewPlayer(){
        do {
            try {
                Player newPlayer = new Player();
                String playerName = getStringInput("\nAdd the new character's name below: \n");
                TroopTiers tier = getTiersFromUser();
                TroopSpec spec = getSpecsFromUser();
                int marchSize = getMarcSizeFromUser(tier, newPlayer);
                int marchCount = getMarchCountFromUser();
                return new Player(playerName, tier, spec, marchSize, marchCount);
            } catch (InputMismatchException e){
                printMessage("Please make sure to enter the right format!\n\nTry again...");
            }catch (IllegalArgumentException e){
                printMessage("Please make sure to enter the right format!\n\nTry again...");
            }
        }while (true);

    }

}