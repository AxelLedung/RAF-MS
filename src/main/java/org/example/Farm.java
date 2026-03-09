package org.example;

import com.sun.tools.javac.Main;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

public class Farm {
    CropManager cropManager = new CropManager();
    AnimalManager animalManager = new AnimalManager();
    File cropSaveFile = new File("RAF-MS-crop-save.txt");
    File animalSaveFile = new File("RAF-MS-animal-save.txt");
    public Farm() {
        LoadCSV();
    }
    public void MainMenu() {
        Scanner scanner = new Scanner(System.in);
        Menu.Header("MAIN MENU");
        System.out.println("1. Manage Animals");
        System.out.println("2. Manage Crops");
        System.out.println("3. Save");
        System.out.println("5. Exit");
        int input;
        try {
            input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    animalManager.AnimalMenu(cropManager.GetCrops());
                    break;
                case 2:
                    cropManager.CropMenu();
                    break;
                case 3:
                    Save();
                    break;
                case 5:
                    System.out.println("Exiting RAF-MS™ - Robust Amazing Farm - Management System");
                    System.out.println("Exiting..................................................");
                    System.exit(0);
                default:
                    System.out.println("Incorrect Input.");
                    break;
            }
            MainMenu();
        }
        catch (Exception e) {
            System.out.println("Incorrect Input.");
            MainMenu();
        }
    }
    private boolean Save() {
        try {
            FileWriter fileWriter = new FileWriter(cropSaveFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < cropManager.GetCrops().size(); i++) {
                bufferedWriter.write(cropManager.GetCrops().get(i).GetCSV());
                if (i < cropManager.GetCrops().size() - 1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            fileWriter = new FileWriter(animalSaveFile);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < animalManager.GetAnimals().size(); i++) {
                bufferedWriter.write(animalManager.GetAnimals().get(i).GetCSV());
                if (i < animalManager.GetAnimals().size() - 1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
            System.out.println("Saved succesfully!");
            LoadCSV();
            return true;
        }
        catch (Exception e) {
            System.out.println("Save failed...");
            LoadCSV();
            return false;
        }
    }
    private boolean LoadCSV() {
        try {
            FileReader fileReader = new FileReader(cropSaveFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            cropManager.GetCrops().clear();
            while (line != null) {
                String[] variables = line.split(",");
                int id = Integer.parseInt(variables[0]);
                String cropName = variables[1];
                String cropType = variables[2];
                int quantity = Integer.parseInt(variables[3]);
                Crop crop = new Crop(id, cropName, cropType, quantity);
                cropManager.GetCrops().add(crop);
                line = bufferedReader.readLine();
            }
            fileReader = new FileReader(animalSaveFile);
            bufferedReader = new BufferedReader(fileReader);
            line = bufferedReader.readLine();
            animalManager.GetAnimals().clear();
            while (line != null) {
                String[] variables = line.split(",");
                int id = Integer.parseInt(variables[0]);
                String animalName = variables[1];
                String species = variables[2];
                String[] acceptableFood = variables[3].split("@");
                ArrayList<String> acceptableFoodArrayList = new ArrayList<String>(Arrays.asList(acceptableFood));
                Animal animal = new Animal(id, animalName, species, acceptableFoodArrayList);
                animalManager.GetAnimals().add(animal);
                line = bufferedReader.readLine();
            }
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
