package com.codecool;

import java.util.InputMismatchException;
import java.util.List;

import static com.codecool.UI.clearScreen;
import static java.lang.Math.round;

public class Player extends Allegiance implements Comparable<Player>{


    @Override
    public int compareTo(Player otherPlayer) {
        return this.playerName.compareTo(otherPlayer.playerName);
    }

    public enum AllegianceTier{
        T1,
        T2,
        T3,
        T4;
    }


    public enum TroopTiers{
        T7,
        T8,
        T9,
        T10
    }

    public enum TroopSpec{
        Cavalry,
        Infantry,
        Range
    }

    private String playerName;
    private TroopTiers tier;
    private TroopSpec spec;
    private int marchCount;
    private double marchPower;
    private int marchSize;
    private AllegianceTier aTier;
    private double gearPower = 0;


    public Player(){
    }

    public Player(String playerName, TroopTiers tier, TroopSpec spec, AllegianceTier aTier){
        this.playerName = playerName;
        this.tier = tier;
        this.spec = spec;
        marchCount = 4;
        marchSize = setDefaultMarchSize(tier);
        marchPower = countMarchPower(tier);
        this.aTier = aTier;
    }

    public Player(String playerName, TroopTiers tier, TroopSpec spec, int marchSize, AllegianceTier aTier){
        this.playerName = playerName;
        this.tier = tier;
        this.spec = spec;
        this.marchSize = marchSize;
        marchCount = 4;
        marchPower = countMarchPower(tier);
        this.aTier = aTier;
    }

    public Player(String playerName, TroopTiers tier, TroopSpec spec, int marchSize, int marchCount, AllegianceTier aTier){
        this.playerName = playerName;
        this.tier = tier;
        this.spec = spec;
        this.marchCount = marchCount;
        this.marchSize = marchSize;
        marchPower = countMarchPower(marchSize, tier);
        this.aTier = aTier;
    }



    public int setDefaultMarchSize(TroopTiers tier) {
        if (tier == TroopTiers.T7){
            marchSize = 60000;
        } else if (tier == TroopTiers.T8){
            marchSize = 80000;
        } else if (tier == TroopTiers.T9){
            marchSize = 90000;
        } else if (tier == TroopTiers.T10){
            marchSize = 100000;
        } else {marchSize = 0;
        } return marchSize;
    }


    public double countMarchPower(TroopTiers tier){
        marchSize = setDefaultMarchSize(tier);
        if (tier == TroopTiers.T7){
            marchPower = 60000*9.8;
        } else if (tier == TroopTiers.T8){
            marchPower = 80000*11.8;
        } else if (tier == TroopTiers.T9){
            marchPower = 90000*14;
        } else if (tier == TroopTiers.T10){
            marchPower = 100000*16.4;
        } else { marchPower = 0;
        } return marchPower;

    }

    public double countMarchPower(int marchSize, TroopTiers tier){
        if (tier == TroopTiers.T7){
            marchPower = marchSize*9.8;
        } else if (tier == TroopTiers.T8){
            marchPower = marchSize*11.8;
        } else if (tier == TroopTiers.T9){
            marchPower = marchSize*14;
        } else if (tier == TroopTiers.T10){
            marchPower = marchSize*16.4;
        } else { marchPower = 0;
        } return round(marchPower);
    }


    public String getPlayerName(){
        return playerName;
    }

    public void setPlayerName(String newPlayerName){
        this.playerName = newPlayerName;
    }

    public TroopTiers getTier(){
        return tier;
    }

    public void setTier(TroopTiers tier) {
        this.tier = tier;
    }

    public TroopSpec getSpec(){
        return spec;
    }

    public void setSpec(TroopSpec spec) {
        this.spec = spec;
    }

    public int getMarchSize(){
        return marchSize;
    }

    public void setMarchSize(int marchSize) {
        this.marchSize = marchSize;
    }

    public double getMarchPower(){
        return marchPower;
    }

    public int getMarchCount(){
        return marchCount;
    }

    public void setMarchCount(int newMarchCount){
        this.marchCount = newMarchCount;
    }

    public AllegianceTier getAllTier(){
        return aTier;
    }

    public void setAllTier(AllegianceTier newTier){
        this.aTier = newTier;
    }

    public void setGearPower(double newGearPower){
        this.gearPower = newGearPower;
    }

    public double getGearPower(){
        return gearPower;
    }





    @Override
    public String toString(){
        return String.format("|\tName: %s\t\t|\tTroop tier: %s\t\t|\tTroop spec: %s\t\t|\tEstimated march power: %s\t\t|\n", playerName, tier, spec, marchPower);
    }


}
