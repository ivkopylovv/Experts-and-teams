package ru.rsreu.expertsandteams.support.helper;

public class StringHelper {
    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
