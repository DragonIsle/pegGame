package com.ivanindustrial.config;

import com.ivanindustrial.entities.TargetAndIntermediate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivan Kazimirov
 */
public class BoardConfig {

    private HashMap<Integer, List<TargetAndIntermediate>> configItems;
    private HashMap<Integer, HashMap<Integer, Integer>> oldItems;

    public List<TargetAndIntermediate> getConfigForCell(Integer cellNumber) {
        return configItems.get(cellNumber);
    }

    public BoardConfig(File file) throws IOException {
        this.configItems = new HashMap<>();
        this.oldItems = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.ready()) {
            String[] configItemArr = reader.readLine().split(":");
            configItems.put(Integer.parseInt(configItemArr[0]), getPairsFromString(configItemArr[1]));
            oldItems.put(Integer.parseInt(configItemArr[0]), getOldPairs(configItemArr[1]));
        }
    }

    private List<TargetAndIntermediate> getPairsFromString(String string) {
        return Arrays.stream(string.trim().split(" ")).map(pairStr -> {
            String[] arr = pairStr.split(",");
            return new TargetAndIntermediate(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        }).collect(Collectors.toList());
    }

    private HashMap<Integer, Integer> getOldPairs(String string) {
        HashMap<Integer, Integer> oldPairs = new HashMap<>();
        Arrays.stream(string.trim().split(" ")).forEach(pairStr -> {
            String[] arr = pairStr.split(",");
            oldPairs.put(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        });
        return oldPairs;
    }

    public HashMap<Integer, Integer> getOldConfig(Integer cellNumber) {
        return oldItems.get(cellNumber);
    }
}
