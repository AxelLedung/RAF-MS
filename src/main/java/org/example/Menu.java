package org.example;

public class Menu {
    //Since I wanted a nice GUI for RAF-MS but didn't want to write the header over and over again...
    //I created the Class: Menu to add static functions such as Header()
    public static void Header(String message) {
        int width = 47;
        int padding = (width - message.length()) / 2;
        String paddingString = " ";
        for (int i = 0; i < padding; i++) {
            paddingString = paddingString + " ";
        }
        System.out.println("-----------------------------------------------");
        System.out.println("RAF-MS™ - Robust Amazing Farm Management System");
        System.out.println("-----------------------------------------------");
        System.out.println(paddingString + message);
        System.out.println("-----------------------------------------------");
    }
    public static void HeaderWithoutMessage() {
        System.out.println("-----------------------------------------------");
        System.out.println("RAF-MS™ - Robust Amazing Farm Management System");
        System.out.println("-----------------------------------------------");
    }
}
