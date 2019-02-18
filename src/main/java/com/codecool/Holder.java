package com.codecool;

import java.util.Map;

import static com.codecool.SoP.Stars;

public class Holder {
    private String holderTitle;
    private Map<String, String> holderBonuses;
    private Stars star;
    private int extraMarchSize;

    public Holder(String holderTitle, Map<String, String> holderBonuses, Stars star){
        this.holderTitle = holderTitle;
        this.holderBonuses = holderBonuses;
        this.star = star;
        extraMarchSize = defineDefaultMarchSizeBonus(star);
    }

    public int convertBonusToInt(String holderBonusStringValue){
        return Integer.parseInt(holderBonusStringValue.trim());
    }

    public String getHolderTitle(){
        return holderTitle;
    }

    public Map<String, String> getHolderBonuses() {
        return holderBonuses;
    }

    private int defineDefaultMarchSizeBonus(Stars star){
        if (star.equals(Stars.ONE)){
            extraMarchSize = 38000;
        } else if (star.equals(Stars.ONE_AND_HALF)){
            extraMarchSize = 42000;
        } else if (star.equals(Stars.TWO)){
            extraMarchSize = 78000;
        } else if (star.equals(Stars.TWO_AND_HALF)){
            extraMarchSize = 86000;
        } else if (star.equals(Stars.THREE)){
            extraMarchSize = 165000;
        } else if (star.equals(Stars.THREE_AND_HALF)){
            extraMarchSize = 189000;
        } else if (star.equals(Stars.FOUR)){
            extraMarchSize = 225000;
        } else if (star.equals(Stars.FOUR_AND_HALF)){
            extraMarchSize = 340000;
        } else if (star.equals((Stars.FIVE))){
            extraMarchSize = 364000;
        } return extraMarchSize;
    }



}
