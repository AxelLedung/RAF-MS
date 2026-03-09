package org.example;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class CropManager {
    private ArrayList<Crop> cropArrayList = new ArrayList<Crop>();
    public void CropMenu() {
        Scanner scanner = new Scanner(System.in);
        Menu.Header("CROPS MANAGER");
        System.out.println("1. View Crops");
        System.out.println("2. Add Crops");
        System.out.println("3. Remove Crops");
        System.out.println("5. Back");
        int input;
        try {
            input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    ViewCrops();
                    break;
                case 2:
                    AddCrop();
                    break;
                case 3:
                    RemoveCrop();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Incorrect Input.");
                    break;
            }
            CropMenu();
        }
        catch (Exception e) {
            System.out.println("Error Input.");
            CropMenu();
        }
    }
    private void ViewCrops() {
        Menu.Header("YOUR CROPS");
        for (int i = 0; i < cropArrayList.size(); i++) {
            System.out.println(cropArrayList.get(i).GetDescription());
        }
        System.out.println("");
        System.out.println("PRESS ENTER TO CONTINUE...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    private void AddCrop() {
        try {
            Scanner scanner = new Scanner(System.in);
            Menu.Header("INSERT CROP NAME");
            String cropName = scanner.nextLine();
            Menu.Header("INSERT CROP TYPE");
            String cropType = scanner.nextLine();
            Menu.Header("INSERT QUANTITY");
            String cropQuantity = scanner.nextLine();
            if (cropArrayList.isEmpty()) {
                System.out.println("-----------------------------------------------");
                System.out.println("   THE SELECTED CROP WAS ADDED TO THE SYSTEM");
                System.out.println("   CROP: " + cropName + " CROPTYPE: " + cropType + " QTY: " + cropQuantity);
                System.out.println("-----------------------------------------------");
                System.out.println("");
                System.out.println("PRESS ENTER TO CONTINUE...");
                cropArrayList.add(new Crop(cropName, cropType, Integer.parseInt(cropQuantity)));
            }
            else {
                boolean cropExists = false;
                int id = 0;
                for (int i = 0; i < cropArrayList.size(); i++) {
                    if (cropArrayList.get(i).getCropType().equals(cropType) && cropArrayList.get(i).name.equals(cropName)) {
                        cropExists = true;
                        id = i;
                        i = cropArrayList.size();
                    }
                }
                if (cropExists) {
                    System.out.println("-----------------------------------------------");
                    System.out.println("   ADDED: " + cropQuantity + " TO " + cropArrayList.get(id).name);
                    System.out.println("-----------------------------------------------");
                    System.out.println("");
                    System.out.println("PRESS ENTER TO CONTINUE...");
                    cropArrayList.get(id).AddCrop(Integer.parseInt(cropQuantity));
                }
                else {
                    System.out.println("-----------------------------------------------");
                    System.out.println("   THE SELECTED CROP WAS ADDED TO THE SYSTEM");
                    System.out.println("   CROP: " + cropName + " CROPTYPE: " + cropType + " QTY: " + cropQuantity);
                    System.out.println("-----------------------------------------------");
                    System.out.println("");
                    System.out.println("PRESS ENTER TO CONTINUE...");
                    cropArrayList.add(new Crop(cropName, cropType, Integer.parseInt(cropQuantity)));
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error with adding crops");
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    private void RemoveCrop() {
        Menu.Header("DELETE CROP BY ID");
        Scanner scanner = new Scanner(System.in);
        try {
            int idInput = Integer.parseInt(scanner.nextLine());
            int idDelete = -1;
            for (int i = 0; i < cropArrayList.size(); i++) {
                if (cropArrayList.get(i).getId() == idInput) {
                    idDelete = i;
                    break;
                }
            }
            if (cropArrayList.get(idDelete) != null) {
                Menu.HeaderWithoutMessage();
                System.out.println("       ARE YOU SURE YOU WANT TO DELETE:");
                System.out.println("       Crop: " + cropArrayList.get(idDelete).name + ", Croptype: " + cropArrayList.get(idDelete).getCropType());
                System.out.println("-----------------------------------------------");
                System.out.println("1. YES DESTROY IT!");
                System.out.println("2. No");
                int deleteChoice = Integer.parseInt(scanner.nextLine());
                switch (deleteChoice) {
                    case 1:
                        System.out.println("-----------------------------------------------");
                        System.out.println("       Crop: " + cropArrayList.get(idDelete).name + ", Croptype: " + cropArrayList.get(idDelete).getCropType());
                        System.out.println("           WAS DELETED FROM RAF-MS™");
                        System.out.println("-----------------------------------------------");
                        System.out.println("");
                        System.out.println("PRESS ENTER TO CONTINUE...");
                        cropArrayList.remove(idDelete);
                        scanner.nextLine();
                        return;
                    case 2:
                        return;
                    default:
                }
            }
            else {
                Menu.Header("CROP WITH THAT ID DOES NOT EXIST");
                System.out.println("");
                System.out.println("PRESS ENTER TO CONTINUE...");
                scanner.nextLine();
                return;
            }
        }
        catch (Exception e) {
            Menu.Header("CROP WITH THAT ID DOES NOT EXIST");
            System.out.println("");
            System.out.println("PRESS ENTER TO CONTINUE...");
            scanner.nextLine();
            return;
        }
    }
    public ArrayList<Crop> GetCrops() {
        return cropArrayList;
    }
}