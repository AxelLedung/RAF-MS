package org.example;

public class Crop extends Entity{
    private String cropType;
    private int quantity;
    private static int nextCropId = 1;
    public Crop(String name, String cropType, int quantity) {
        super(nextCropId, name);
        nextCropId++;
        this.name = name;
        this.cropType = cropType;
        this.quantity = quantity;
    }
    public Crop(int id, String name, String cropType, int quantity) {
        super(id, name);
        if (id >= nextCropId) {
            nextCropId = id + 1;
        }
        this.name = name;
        this.cropType = cropType;
        this.quantity = quantity;
    }
    @Override public String GetDescription() {
        return "ID: " + getId() + " Name: " + name + " Crop type: " + cropType + " Qty: " + quantity;
    }
    @Override public String GetCSV() {
        return getId() + "," + name + "," + cropType + "," + quantity;
    }
    public void AddCrop(int quantity) {
        this.quantity += quantity;
    }
    public boolean TakeCrop(int quantity) {
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
            return true;
        }
        else {
            return false;
        }
    }
    public String getCropType() {
        return cropType;
    }
    public void setCropType(String cropType) {
        this.cropType = cropType;
    }
}
