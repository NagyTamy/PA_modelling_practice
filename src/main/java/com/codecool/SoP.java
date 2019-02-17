package com.codecool;



import java.util.List;
import java.util.Map;

import com.codecool.Player.AllegianceTier;

public class SoP {
    private final String name;
    private final String region;
    private final Cords cords;
    private final Stars stars;
    private final String holderTitle;

    //Holder bonuses stored in a map, the key stores the bonus' type, the value stores the granted bonus
    private Map<String, Double> holderBonuses;

    //the minimum bannerman neceserry for takeover
    private int bannerCount;

    //the minimum required hierarchy level in-game
    private AllegianceTier tier;

    //Regional bonuses stored in a map, the key stores the bonus' type, the value stores the granted bonus
    private Map<String, Double> regionalBonuses;

    //Stores the titles as key and the granted bonuses in an array (0) bonus type string, (1) bonus amount string convertible to int/double
    private Map<String, List<String>> bannerBonuses;



    public enum Stars {
        ONE(1.0),
        ONE_AND_HALF(1.5),
        TWO(2.0),
        TWO_AND_HALF(2.5),
        THREE(3.0),
        THREE_AND_HALF(3.5),
        FOUR(4.0),
        FOUR_AND_HALF(4.5),
        FIVE(5.0);


        private final double stars;

        private Stars(double stars) {
            this.stars = stars;
        }

        public double getStars() {
            return stars;
        }
    }


    private int requiredBannerCount(Stars stars){
        if (stars.equals(Stars.ONE)){
            bannerCount = 0;
        } else if (stars.equals(Stars.ONE_AND_HALF)){
            bannerCount = 0;
        } else if (stars.equals(Stars.TWO)){
            bannerCount = 1;
        } else if (stars.equals(Stars.TWO_AND_HALF)){
            bannerCount = 1;
        } else if (stars.equals(Stars.THREE)){
            bannerCount = 12;
        } else if (stars.equals(Stars.THREE_AND_HALF)){
            bannerCount = 12;
        } else if (stars.equals(Stars.FOUR)){
            bannerCount = 20;
        } else if (stars.equals(Stars.FOUR_AND_HALF)){
            bannerCount = 30;
        } else if (stars.equals((Stars.FIVE))){
            bannerCount = 62;
        } return bannerCount;
    }

    private AllegianceTier requiredAllegianceTier(Stars stars){
        if (stars.equals(Stars.ONE)){
            bannerCount = 0;
        } else if (stars.equals(Stars.ONE_AND_HALF)){
            bannerCount = 0;
        } else if (stars.equals(Stars.TWO)){
            bannerCount = 1;
        } else if (stars.equals(Stars.TWO_AND_HALF)){
            bannerCount = 1;
        } else if (stars.equals(Stars.THREE)){
            bannerCount = 12;
        } else if (stars.equals(Stars.THREE_AND_HALF)){
            bannerCount = 12;
        } else if (stars.equals(Stars.FOUR)){
            bannerCount = 20;
        } else if (stars.equals(Stars.FOUR_AND_HALF)){
            bannerCount = 30;
        } else if (stars.equals((Stars.FIVE))){
            bannerCount = 62;
        } return tier;
    }



    public SoP(String name, String region, Cords cords, Stars stars, String holderTitle, Map<String, Double> holderBonuses,
               Map<String, Double> regionalBonuses, Map<String, List<String>> bannerBonuses){
        this.name = name;
        this.region = region;
        this.cords = cords;
        this.stars = stars;
        this.holderTitle = holderTitle;
        this.holderBonuses = holderBonuses;
        bannerCount = requiredBannerCount(stars);
        tier = requiredAllegianceTier(stars);
        this.regionalBonuses = regionalBonuses;
        this.bannerBonuses = bannerBonuses;


    }

    public String getName(){
        return name;
    }

    public String getRegion(){
        return region;
    }

    public Cords getCords(){
        return cords;
    }

    public Stars getStars(){
        return stars;
    }

    public String getHolderTitle(){
        return holderTitle;
    }

    public Map<String, Double> getHolderBonuses(){
        return holderBonuses;
    }

    public Map<String, Double> getRegionalBonuses(){
        return regionalBonuses;
    }

    public  Map<String, List<String>> getBannerBonuses(){
        return bannerBonuses;
    }

    public int getBannerCount(){
        return bannerCount;
    }

    public AllegianceTier getTier() {
        return tier;
    }
}
