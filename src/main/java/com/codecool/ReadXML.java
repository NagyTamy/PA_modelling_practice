package com.codecool;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.codecool.Player.TroopTiers;
import com.codecool.Player.TroopSpec;
import com.codecool.Player.AllegianceTier;

public class ReadXML {
    UI fileReader = new UI();
    public List<Player> readAllegianceFromFile(String xmlFilePath){
        List<Player> list = new ArrayList<>();

        try {
            File fXmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Player");

            for (int i = 0; i < nList.getLength(); i++) {

                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Player member = new Player(eElement.getAttribute("name"),
                            TroopTiers.valueOf(eElement.getElementsByTagName("troop_tier").item(0).getTextContent()),
                            TroopSpec.valueOf(eElement.getElementsByTagName("troop_spec").item(0).getTextContent()),
                            Integer.parseInt(eElement.getElementsByTagName("march_size").item(0).getTextContent()),
                            Integer.parseInt(eElement.getElementsByTagName("march_count").item(0).getTextContent()),
                            AllegianceTier.valueOf(eElement.getElementsByTagName("allegiance").item(0).getTextContent()));

                    list.add(member);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getAllegianceName(String xmlFilePath){
        String allegianceName = "";
        try {
            File fXmlFile = new File(xmlFilePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Allegiance");
            Node nNode = nList.item(0);
            Element eElement = (Element) nNode;
            allegianceName = eElement.getAttribute("name");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allegianceName;
    }

    public List<Player> findPlayersByName(String xmlFilePath){
        String findName = fileReader.getStringInput("Add the players name below:");
        return findPlayersByName(xmlFilePath, findName);
    }

    public List<Player> findPlayersByName(String xmlFilePath, String targetName){
        List<Player> membersList = readAllegianceFromFile(xmlFilePath);
        List<Player> searchResult = new ArrayList<>();
        for (Player p : membersList){
            if (p.getPlayerName().toLowerCase().contains(targetName.toLowerCase())){
                searchResult.add(p);
            }
        } return searchResult;
    }

    //search for players in xml file, returns true if player already stored
    public boolean ifPlayerExist(String xmlFilePath, String findName) {
        List<Player> membersList = readAllegianceFromFile(xmlFilePath);
        for (Player p : membersList) {
            if (findName.toLowerCase().equals(p.getPlayerName().toLowerCase())) {
                return true;
            }
        }return false;
    }

    //search for players in list, returns true if player with the same name already exist
    public boolean ifPlayerExist(List<Player> membersList, String findName) {
        for (Player p : membersList) {
            if (findName.toLowerCase().equals(p.getPlayerName().toLowerCase())) {
                return true;
            }
        }return false;
    }

    //finds and returns the player with the given name in xml file

    public Player findPlayerByName(String xmlFilePath, String findName) {
        List<Player> membersList = readAllegianceFromFile(xmlFilePath);
        for (Player p : membersList) {
            if (findName.toLowerCase().equals(p.getPlayerName().toLowerCase())) {
                return p;
            }
        } return null;
    }
    //finds and returns the player with the given name in List<Player>

    public Player findPlayerByName(List<Player> membersList, String findName) {
        for (Player p : membersList) {
            if (findName.toLowerCase().equals(p.getPlayerName().toLowerCase())) {
                return p;
            }
        } return null;
    }

    // asks user input, returns a player by name if exist, a list of players with similar name if ha more, add new member if not

    public Player findPlayerByName(String xmlFilePath) {
        String findName = fileReader.getStringInput("Add the players name below:");
        List<Player> membersList = readAllegianceFromFile(xmlFilePath);
        for (Player p : membersList) {
            if (findName.toLowerCase().equals(p.getPlayerName().toLowerCase())) {
                return p;
            }
        }
        fileReader.printMessage("There is no player with that name. The following players has similar names:");
        fileReader.printPlayersList(findPlayersByName(xmlFilePath, findName));
        String anotherOption = fileReader.getStringInput("Copy the exact name from the list above, enter ADD if you " +
                "don't see the player in the list and want to add it or NO to exit.");
        for (Player p : membersList) {
            if (anotherOption.toLowerCase().equals(p.getPlayerName().toLowerCase())) {
                return p;
            }
            if (anotherOption.toLowerCase().equals("add")) {
                Player addPlayer = fileReader.createNewPlayer();
                new WriteXML().appendPlayer(xmlFilePath, addPlayer);
                return addPlayer;
            } else if (anotherOption.toLowerCase().equals("no")) {
                return null;
            }
        } return null;
    }

    public List<Player> findPlayersBySpec(String xmlFilePath){
        TroopSpec findSpecs = fileReader.getSpecsFromUser();
        List<Player> membersList = readAllegianceFromFile(xmlFilePath);
        List<Player> searchResult = new ArrayList<>();
        for (Player p : membersList){
            if (p.getSpec().equals(findSpecs)){
                searchResult.add(p);
            }
        } return searchResult;
    }

    public List<Player> findPlayersByTier(String xmlFilePath){
        TroopTiers findTier = fileReader.getTiersFromUser();
        List<Player> membersList = readAllegianceFromFile(xmlFilePath);
        List<Player> searchResult = new ArrayList<>();
        for (Player p : membersList){
            if (p.getTier().equals(findTier)){
                searchResult.add(p);
            }
        } return searchResult;
    }

    public List<Player> findPlayersInHierarchy(String xmlFilePath){
        AllegianceTier findTier = fileReader.getAllegianceTierFromUser();
        List<Player> membersList = readAllegianceFromFile(xmlFilePath);
        List<Player> searchResult = new ArrayList<>();
        for (Player p : membersList){
            if (p.getAllTier().equals(findTier)){
                searchResult.add(p);
            }
        } return searchResult;
    }
}
