package org.example;

import java.util.ArrayList;

public class Animal extends Entity{
    private String species;
    private ArrayList<String> acceptableCropTypes = new ArrayList<String>();
    private static int nextAnimalId = 1;
    public Animal(String name, String species, ArrayList<String> acceptableCropTypes) {
        super(nextAnimalId, name);
        nextAnimalId++;
        this.name = name;
        this.species = species;
        this.acceptableCropTypes = acceptableCropTypes;
    }
    public Animal(int id, String name, String species, ArrayList<String> acceptableCropTypes) {
        super(id, name);
        if (id >= nextAnimalId) {
            nextAnimalId = id + 1;
        }
        this.name = name;
        this.species = species;
        this.acceptableCropTypes = acceptableCropTypes;
    }
    @Override public String GetDescription() {
        String acceptableCropTypesString = "";
        for (int i = 0; i < acceptableCropTypes.size(); i++) {
            if (i == 0 ) {
                acceptableCropTypesString = acceptableCropTypes.get(i);
            }
            else {
                acceptableCropTypesString = acceptableCropTypesString + ", " + acceptableCropTypes.get(i);
            }
        }
        return "ID: " + getId() + ", Name: " + name + ", Species: " + species + ", Edible Food: " + acceptableCropTypesString;
    }
    @Override public String GetCSV() {
        String acceptableCropTypesString = "";
        for (int i = 0; i < acceptableCropTypes.size(); i++) {
            if (i == acceptableCropTypes.size() - 1) {
                acceptableCropTypesString = acceptableCropTypesString + acceptableCropTypes.get(i);
            }
            else {
                acceptableCropTypesString = acceptableCropTypesString + acceptableCropTypes.get(i) + "@";
            }
        }
        return getId() + "," + name + "," + species + "," + acceptableCropTypesString;
    }
    public void Feed(Crop crop) {
        boolean inList = false;
        for (int i = 0; i < acceptableCropTypes.size(); i++) {
            if (crop.getCropType().equals(acceptableCropTypes.get(i))) {
                inList = true;
                break;
            }
        }
        if (inList) {
            if (crop.TakeCrop(1)) {
                System.out.println("Successfully fed " + this.name + " with " + crop.name);
            }
            else {
                System.out.println("You did not have enough " + crop.name + " to be able to feed " + this.name);
            }
        }
        else {
            System.out.println(this.name + " will not eat " + crop.name);
        }
    }
    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}
