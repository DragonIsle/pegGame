package com.ivanindustrial.config;

import com.ivanindustrial.entities.TargetAndIntermediate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

    private BoardConfig(HashMap<Integer, List<TargetAndIntermediate>> configItems) {
        this.configItems = configItems;
    }

    public List<TargetAndIntermediate> getConfigForCell(Integer cellNumber) {
        return configItems.get(cellNumber);
    }

    public static BoardConfig createConfigFromFile(String fileName) throws IOException {
        HashMap<Integer, List<TargetAndIntermediate>>  configItems = new HashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        while (reader.ready()) {
            String[] configItemArr = reader.readLine().split(":");
            configItems.put(Integer.parseInt(configItemArr[0]), getPairsFromString(configItemArr[1]));
        }
        return new BoardConfig(configItems);
    }

    private static List<TargetAndIntermediate> getPairsFromString(String string) {
        return Arrays.stream(string.trim().split(" ")).map(pairStr -> {
            String[] arr = pairStr.split(",");
            return new TargetAndIntermediate(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        }).collect(Collectors.toList());
    }
}
