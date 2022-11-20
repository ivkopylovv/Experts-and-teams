package ru.rsreu.expertsandteams.helper;

public class StringHelper {
    private StringHelper() {}

    public static String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
