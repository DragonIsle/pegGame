package com.ivanindustrial.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Ivan Kazimirov
 */
public class BoardConfig {

    private HashMap<Integer, HashMap<Integer, Integer>> configItems;

    public BoardConfig(File file) throws IOException {
        this.configItems = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            String[] configItemArr = reader.readLine().split(":");
            configItems.put(Integer.parseInt(configItemArr[0]), getPairsFromString(configItemArr[1]));
        }
    }

    private HashMap<Integer, Integer> getPairsFromString(String string) {
        HashMap<Integer, Integer> configPairs = new HashMap<>();
        Arrays.stream(string.trim().split(" ")).forEach(pairStr -> {
            String[] arr = pairStr.split(",");
            configPairs.put(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        });
        return configPairs;
    }

    public HashMap<Integer, Integer> getCellConfig(Integer cellNumber) {
        return configItems.get(cellNumber);
    }
}
