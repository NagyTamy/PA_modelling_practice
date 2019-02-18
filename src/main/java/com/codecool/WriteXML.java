package com.codecool;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.InputMismatchException;
import java.util.List;

import com.codecool.Player.TroopTiers;
import com.codecool.Player.TroopSpec;


public class WriteXML {

    UI writeFiles = new UI();
    ReadXML openToWrite = new ReadXML();

    public void writeAllegianceToFile(String allegianceName, List<Player> members, String xmlFilePath){
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root element
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Allegiance");
            doc.appendChild(rootElement);

            Attr attr2 = doc.createAttribute("name");
            attr2.setValue(allegianceName);
            rootElement.setAttributeNode(attr2);

            for (Player p : members) {
                // Player elements
                Element player = doc.createElement("Player");
                rootElement.appendChild(player);

                // set name attribute to Player element
                Attr attr = doc.createAttribute("name");
                attr.setValue(p.getPlayerName());
                player.setAttributeNode(attr);


                // Trooptiers elements
                Element troop_tier = doc.createElement("troop_tier");
                troop_tier.appendChild(doc.createTextNode(String.valueOf(p.getTier())));
                player.appendChild(troop_tier);

                // Troop specialty elements
                Element troop_spec = doc.createElement("troop_spec");
                troop_spec.appendChild(doc.createTextNode(String.valueOf(p.getSpec())));
                player.appendChild(troop_spec);


                // march_size elements
                Element march_size = doc.createElement("march_size");
                march_size.appendChild(doc.createTextNode(String.valueOf(p.getMarchSize())));
                player.appendChild(march_size);

                // march_count elements
                Element march_count = doc.createElement("march_count");
                march_count.appendChild(doc.createTextNode(String.valueOf(p.getMarchCount())));
                player.appendChild(march_count);

                //allegiance hierarchy element
                Element allegiance = doc.createElement("allegiance");
                allegiance.appendChild(doc.createTextNode(String.valueOf(p.getAllTier())));
                player.appendChild(allegiance);

                //gear power element
                Element gear_power = doc.createElement("gear_power");
                gear_power.appendChild(doc.createTextNode(String.valueOf(p.getGearPower())));
                player.appendChild(gear_power);
            }


            // write the content into xml file

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty
                    (OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(xmlFilePath));

            // Output to console for testing
            //StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            writeFiles.printMessage("Saved to file!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }


    public void appendPlayer(String xmlFilePath){
        String allegianceName = openToWrite.getAllegianceName(xmlFilePath);
        List<Player> updateList = openToWrite.readAllegianceFromFile(xmlFilePath);
        Player player;
        boolean validInput = false;
        while (!validInput) {
            try {
                player = writeFiles.createNewPlayer();
                validInput = true;
                if (openToWrite.ifPlayerExist(xmlFilePath, player.getPlayerName())){
                    validInput = false;
                    // writeFiles.printMessage("Player with this name already exist, please chose another name!");
                    String addOrModify = writeFiles.getStringInput("Player with this name already exists:\n" + player + "\nWould you like to modify it? " +
                            "Y - to modify existing player, N - add new player with different name:");
                    if (addOrModify.toLowerCase().equals("y")){
                        // System.out.println("Implement update member method here");
                        writeFiles.updatePlayer(player.getPlayerName(), updateList, xmlFilePath);
                        String continueOrBack = writeFiles.getStringInput("Existing members data updated, would you like to add a new member?");
                        if (continueOrBack.toLowerCase().equals("y")){
                            continue;
                        } else if (continueOrBack.toLowerCase().equals("n")){
                            validInput = true;
                            break;
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } else if (addOrModify.toLowerCase().equals("n")){
                        continue;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    updateList.add(player);
                    writeFiles.printPlayersList(updateList);
                    writeAllegianceToFile(allegianceName, updateList, xmlFilePath);
                }
                    //Modify existing member if creating new finds member already exist at the same name - under development
                    /*
                    String addOrModify = writeFiles.getStringInput("Player with this name already exist, would you like to modify it? " +
                            "Y - to modify existing player, N - add new player with different name:");
                    if (addOrModify.toLowerCase().equals("y")){
                        System.out.println("Implement update member method here");
                    } else if (addOrModify.toLowerCase().equals("n")){
                        continue;
                    } else {
                        throw new IllegalArgumentException();
                    }*/

            } catch (InputMismatchException e) {
                writeFiles.printMessage("Not a valid option!");
            } catch (IllegalArgumentException e){
                writeFiles.printMessage("Not a valid option!");
            }
        }

    }

    public void appendPlayer(String xmlFilePath, Player player){
        String allegianceName = openToWrite.getAllegianceName(xmlFilePath);
        List<Player> updateList = openToWrite.readAllegianceFromFile(xmlFilePath);
        updateList.add(player);
        writeAllegianceToFile(allegianceName, updateList, xmlFilePath);
    }


    public void appendMultiplePlayer(String xmlFilePath){
        String allegianceName = openToWrite.getAllegianceName(xmlFilePath);
        List<Player> updateList = openToWrite.readAllegianceFromFile(xmlFilePath);
        boolean newPlayer = true;
        Player player;
        boolean validInput = false;
        while (newPlayer){
            while (!validInput) {
                try {
                    player = writeFiles.createNewPlayer();
                    validInput = true;
                    if (openToWrite.ifPlayerExist(xmlFilePath, player.getPlayerName())){
                        validInput = false;
                        // writeFiles.printMessage("Player with this name already exist, please chose another name!");
                        String addOrModify = writeFiles.getStringInput("Player with this name already exists:\n" + player + "\nWould you like to modify it? " +
                                "Y - to modify existing player, N - add new player with different name:");
                        if (addOrModify.toLowerCase().equals("y")){
                            // System.out.println("Implement update member method here");
                            writeFiles.updatePlayer(player.getPlayerName(), updateList, xmlFilePath);
                            String continueOrBack = writeFiles.getStringInput("Existing members data updated, would you like to add a new member?");
                            if (continueOrBack.toLowerCase().equals("y")){
                                continue;
                            } else if (continueOrBack.toLowerCase().equals("n")){
                                validInput = true;
                                break;
                            } else {
                                throw new IllegalArgumentException();
                            }
                        } else if (addOrModify.toLowerCase().equals("n")){
                            continue;
                        } else {
                            throw new IllegalArgumentException();
                        }
                    } else {
                        updateList.add(player);
                        writeFiles.printPlayersList(updateList);
                        writeAllegianceToFile(allegianceName, updateList, xmlFilePath);
                    }
                } catch (InputMismatchException e) {
                    writeFiles.printMessage("Not a valid option!");
                } catch (IllegalArgumentException e){
                    writeFiles.printMessage("Not a valid option!");
                }
            }String more = writeFiles.getStringInput("Would you like to add more player? Press any key to continue or No to save to file.");
            if (more.toLowerCase().equals("n") || more.toLowerCase().equals("no")){
                newPlayer = false;
            } else {
                validInput = false;
            }
        }
        writeAllegianceToFile(allegianceName, updateList, xmlFilePath);
    }



    public void removePlayerByName(String xmlFilePath) {
        try {
            String allegianceName = openToWrite.getAllegianceName(xmlFilePath);
            List<Player> list = openToWrite.readAllegianceFromFile(xmlFilePath);
            String searchForPlayer = writeFiles.getStringInput("Add the name of the player you want to remove:");
            boolean playerExist = openToWrite.ifPlayerExist(xmlFilePath, searchForPlayer);
            if (playerExist) {
                Player playerToRemove = openToWrite.findPlayerByName(list, searchForPlayer);
                int index = list.indexOf(playerToRemove);
                String option = writeFiles.getStringInput("Are you sure you want to remove player " +
                        playerToRemove + "? Y - to remove, N - go back to menu");
                if (option.toLowerCase().equals("y")) {
                    list.remove(index);
                    writeAllegianceToFile(allegianceName, list, xmlFilePath);
                } else if (option.toLowerCase().equals("n")) {
                    return;
                } else {
                    throw new IllegalArgumentException();
                }
            } else if (!playerExist) {
                writeFiles.printMessage("There is no player with that exact name, here is the list of players having similar names:");
                writeFiles.printPlayersList(openToWrite.findPlayersByName(xmlFilePath, searchForPlayer));
                String option = writeFiles.getStringInput("Add the name of the player you want to remove or 0 to back to menu:");
                if (option.equals("0")) {
                    new Menu().players_menu();
                } else if (!(openToWrite.ifPlayerExist(xmlFilePath, option))) {
                    writeFiles.printMessage("Invalid option redirecting to players menu...");
                    new Menu().players_menu();
                } else {
                    Player playerToRemove = openToWrite.findPlayerByName(list, option);
                    int index = list.indexOf(playerToRemove);
                    String newOption = writeFiles.getStringInput("Are you sure you want to remove player " +
                            playerToRemove + "? Y - to remove, N - go back to menu");
                    if (newOption.toLowerCase().equals("y")) {
                        list.remove(index);
                        writeAllegianceToFile(allegianceName, list, xmlFilePath);
                    } else if (newOption.toLowerCase().equals("n")) {
                        return;
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            } else {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e){
            writeFiles.printMessage("Invalid option, try again!");
        }
    }

}
