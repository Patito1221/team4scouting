package com.example.jaflo.thescoutingapp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaflo on 1/19/2017.
 */

public class Teams {
    public static final Map<String, Integer[]> teams = new HashMap<>();

    public static void initValues() {
        teams.put("Team 4 ELEMENT", new Integer[] { 1, 4, 6, 2,});
        teams.put("Cheezy Poofs", new Integer[] {1, 7, 123, 34566});
    }
}
