package org.example;

import javax.swing.text.View;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalManager {
    private ArrayList<Animal> animalArrayList = new ArrayList<Animal>();
    public void AnimalMenu(ArrayList<Crop> crops) {
        Scanner scanner = new Scanner(System.in);
        Menu.Header("ANIMAL MANAGER:");
        System.out.println("1. View Animals");
        System.out.println("2. Add Animals");
        System.out.println("3. Remove Animals");
        System.out.println("4. Feed Animals");
        System.out.println("5. Back");
        int input;
        try {
            input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    ViewAnimals();
                    break;
                case 2:
                    AddAnimal();
                    break;
                case 3:
                    RemoveAnimal();
                    break;
                case 4:
                    FeedAnimals(crops);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Incorrect Input.");
                    break;
            }
            AnimalMenu(crops);
        }
        catch (Exception e) {
            System.out.println("Incorrect Input.");
            AnimalMenu(crops);
        }
    }
    private void ViewAnimals() {
        Menu.Header("YOUR ANIMALS");
        for (int i = 0; i < animalArrayList.size(); i++) {
            System.out.println(animalArrayList.get(i).GetDescription());
        }
        System.out.println("");
        System.out.println("PRESS ENTER TO CONTINUE...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    private void AddAnimal() {
        Scanner scanner = new Scanner(System.in);
        Menu.Header("INSERT ANIMAL NAME");
        String animalName = scanner.nextLine();
        Menu.Header("INSERT ANIMAL SPECIES");
        String animalSpecies = scanner.nextLine();
        Menu.Header("INSERT CROP TYPE THAT THIS ANIMAL CAN EAT");
        ArrayList<String> animalAcceptableCrops = new ArrayList<String>();
        String input = scanner.nextLine();
        while (!input.equals("stop")) {
            animalAcceptableCrops.add(input);
            Menu.HeaderWithoutMessage();
            System.out.println("     ADD ADDITIONAL CROP TYPES ANIMAL CAN EAT  ");
            System.out.println("IF YOU DON'T WISH TO ADD CROP TYPES SAY: stop  ");
            System.out.println("-----------------------------------------------");
            input = scanner.nextLine();
        }
        if (animalName.isEmpty() || animalSpecies.isEmpty() || animalAcceptableCrops.isEmpty()) {
            System.out.println("You forgot to add something when adding your animal.");
        }
        else {
            animalArrayList.add(new Animal(animalName, animalSpecies, animalAcceptableCrops));
        }
    }
    private void RemoveAnimal() {
        Menu.Header("DELETE ANIMAL BY ID");
        Scanner scanner = new Scanner(System.in);
        try {
            int idInput = Integer.parseInt(scanner.nextLine());
            int idDelete = -1;
            for (int i = 0; i < animalArrayList.size(); i++) {
                if (animalArrayList.get(i).getId() == idInput) {
                    idDelete = i;
                    break;
                }
            }
            if (animalArrayList.get(idDelete) != null) {
                Menu.HeaderWithoutMessage();
                System.out.println("        ARE YOU SURE YOU WANT TO DELETE:");
                System.out.println("       Name: " + animalArrayList.get(idDelete).name + ", Species: " + animalArrayList.get(idDelete).getSpecies());
                System.out.println("-----------------------------------------------");
                System.out.println("1. YES DESTROY IT!");
                System.out.println("2. No");
                int deleteChoice = Integer.parseInt(scanner.nextLine());
                switch (deleteChoice) {
                    case 1:
                        System.out.println("-----------------------------------------------");
                        System.out.println("         Name: " + animalArrayList.get(idDelete).name + ", Species: " + animalArrayList.get(idDelete).getSpecies());
                        System.out.println("         WAS DELETED FROM RAF-MS™");
                        System.out.println("-----------------------------------------------");
                        System.out.println("");
                        System.out.println("PRESS ENTER TO CONTINUE...");
                        animalArrayList.remove(idDelete);
                        scanner.nextLine();
                        return;
                    case 2:
                        return;
                    default:
                }
            }
            else {
                Menu.Header("ANIMAL WITH THAT ID DOES NOT EXIST");
                System.out.println("");
                System.out.println("PRESS ENTER TO CONTINUE...");
                scanner.nextLine();
            }
        }
        catch (Exception e) {
            Menu.Header("ANIMAL WITH THAT ID DOES NOT EXIST");
            System.out.println("");
            System.out.println("PRESS ENTER TO CONTINUE...");
            scanner.nextLine();
        }
    }
    private void FeedAnimals(ArrayList<Crop> crops) {
        Scanner scanner = new Scanner(System.in);
        Menu.Header("INPUT FOOD ID");
        for (int i = 0; i < crops.size(); i++) {
            System.out.println(crops.get(i).GetDescription());
        }
        String cropInput = scanner.nextLine();
        Menu.Header("INPUT THE ID OF THE ANIMAL YOU WANT TO FEED");
        for (int i = 0; i < animalArrayList.size(); i++) {
            System.out.println(animalArrayList.get(i).GetDescription());
        }
        String animalInput = scanner.nextLine();
        try {
            int animalId = 0;
            int cropId = 0;
            for (int i = 0; i < animalArrayList.size(); i++) {
                if (animalArrayList.get(i).getId() == Integer.parseInt(animalInput)) {
                    animalId = i;
                    break;
                }
            }
            for (int i = 0; i < crops.size(); i++) {
                if (crops.get(i).getId() == Integer.parseInt(cropInput)) {
                    cropId = i;
                }
            }
            animalArrayList.get(animalId).Feed((crops.get(cropId)));
        }
        catch (Exception e) {
            System.out.println("Error when feeding animal make sure to use ID when selecting Animal and Crop" + e);
            System.out.println(animalInput);
        }
    }
    public ArrayList<Animal> GetAnimals() {
        return animalArrayList;
    }

}
