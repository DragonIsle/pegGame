package com.ivanindustrial.config;

import com.ivanindustrial.entities.TargetAndIntermediate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author Ivan Kazimirov
 */
public class BoardConfig {

    private HashMap<Integer, List<TargetAndIntermediate>> configItems;

    public static BoardConfig initTriangleBoardConfig() {
        HashMap<Integer, List<TargetAndIntermediate>> configItems = new HashMap<>();
        configItems.put(1, createConfigForItem(Arrays.asList(4, 6), Arrays.asList(2, 3)));
        configItems.put(2, createConfigForItem(Arrays.asList(7, 9), Arrays.asList(4, 5)));
        configItems.put(3, createConfigForItem(Arrays.asList(8, 10), Arrays.asList(5, 6)));
        configItems.put(4, createConfigForItem(Arrays.asList(1, 6, 11, 13), Arrays.asList(2, 5, 7, 8)));
        configItems.put(5, createConfigForItem(Arrays.asList(12, 14), Arrays.asList(8, 9)));
        configItems.put(6, createConfigForItem(Arrays.asList(1, 4, 13, 15), Arrays.asList(3, 5, 9, 10)));
        configItems.put(7, createConfigForItem(Arrays.asList(2, 9), Arrays.asList(4, 8)));
        configItems.put(8, createConfigForItem(Arrays.asList(3, 10), Arrays.asList(5, 9)));
        configItems.put(9, createConfigForItem(Arrays.asList(2, 7), Arrays.asList(5, 8)));
        configItems.put(10, createConfigForItem(Arrays.asList(3, 8), Arrays.asList(6, 9)));
        configItems.put(11, createConfigForItem(Arrays.asList(4, 13), Arrays.asList(7, 12)));
        configItems.put(12, createConfigForItem(Arrays.asList(5, 14), Arrays.asList(8, 13)));
        configItems.put(13, createConfigForItem(Arrays.asList(4, 6, 11, 15), Arrays.asList(8, 9, 12, 14)));
        configItems.put(14, createConfigForItem(Arrays.asList(5, 12), Arrays.asList(9, 13)));
        configItems.put(15, createConfigForItem(Arrays.asList(6, 13), Arrays.asList(10, 14)));
        return new BoardConfig(configItems);
    }

    public static BoardConfig initEnglishBoardConfig() {
        HashMap<Integer, List<TargetAndIntermediate>> configItems = new HashMap<>();
        configItems.put(1, createConfigForItem(Arrays.asList(3, 9), Arrays.asList(2, 4)));
        configItems.put(2, createConfigForItem(Arrays.asList(10), Arrays.asList(5)));
        configItems.put(3, createConfigForItem(Arrays.asList(1, 11), Arrays.asList(2, 6)));
        configItems.put(4, createConfigForItem(Arrays.asList(6, 16), Arrays.asList(5, 9)));
        configItems.put(5, createConfigForItem(Arrays.asList(17), Arrays.asList(10)));
        configItems.put(6, createConfigForItem(Arrays.asList(4, 18), Arrays.asList(5, 11)));
        configItems.put(7, createConfigForItem(Arrays.asList(9, 21), Arrays.asList(8, 14)));
        configItems.put(8, createConfigForItem(Arrays.asList(10, 22), Arrays.asList(9, 15)));

        configItems.put(9, createConfigForItem(Arrays.asList(1, 7, 11, 23), Arrays.asList(4, 8, 10, 16)));
        configItems.put(10, createConfigForItem(Arrays.asList(2, 8, 12, 24), Arrays.asList(5, 9, 11, 17)));
        configItems.put(11, createConfigForItem(Arrays.asList(3, 9, 13, 25), Arrays.asList(6, 10, 12, 18)));

        configItems.put(12, createConfigForItem(Arrays.asList(10, 26), Arrays.asList(11, 19)));
        configItems.put(13, createConfigForItem(Arrays.asList(11, 27), Arrays.asList(12, 20)));
        configItems.put(14, createConfigForItem(Arrays.asList(16), Arrays.asList(15)));
        configItems.put(15, createConfigForItem(Arrays.asList(17), Arrays.asList(16)));

        configItems.put(16, createConfigForItem(Arrays.asList(4, 14, 18, 28), Arrays.asList(9, 15, 17, 23)));
        configItems.put(17, createConfigForItem(Arrays.asList(5, 15, 19, 29), Arrays.asList(10, 16, 18, 24)));
        configItems.put(18, createConfigForItem(Arrays.asList(6, 16, 20, 30), Arrays.asList(11, 17, 19, 25)));

        configItems.put(19, createConfigForItem(Arrays.asList(17), Arrays.asList(18)));
        configItems.put(20, createConfigForItem(Arrays.asList(18), Arrays.asList(19)));
        configItems.put(21, createConfigForItem(Arrays.asList(7, 23), Arrays.asList(14, 22)));
        configItems.put(22, createConfigForItem(Arrays.asList(8, 24), Arrays.asList(15, 23)));

        configItems.put(23, createConfigForItem(Arrays.asList(9, 21, 25, 31), Arrays.asList(16, 22, 24, 28)));
        configItems.put(24, createConfigForItem(Arrays.asList(10, 22, 26, 32), Arrays.asList(17, 23, 25, 29)));
        configItems.put(25, createConfigForItem(Arrays.asList(11, 23, 27, 33), Arrays.asList(18, 24, 26, 30)));

        configItems.put(26, createConfigForItem(Arrays.asList(12, 24), Arrays.asList(19, 25)));
        configItems.put(27, createConfigForItem(Arrays.asList(13, 25), Arrays.asList(20, 26)));
        configItems.put(28, createConfigForItem(Arrays.asList(16, 30), Arrays.asList(23, 29)));
        configItems.put(29, createConfigForItem(Arrays.asList(17), Arrays.asList(24)));
        configItems.put(30, createConfigForItem(Arrays.asList(18, 28), Arrays.asList(25, 29)));
        configItems.put(31, createConfigForItem(Arrays.asList(23, 33), Arrays.asList(28, 32)));
        configItems.put(32, createConfigForItem(Arrays.asList(24), Arrays.asList(29)));
        configItems.put(33, createConfigForItem(Arrays.asList(25, 31), Arrays.asList(30, 32)));
        return new BoardConfig(configItems);
    }

    public static BoardConfig initDeutschBoardConfig() {
        HashMap<Integer, List<TargetAndIntermediate>>  configItems = new HashMap<>();
        return new BoardConfig(configItems);
    }

    private BoardConfig(HashMap<Integer, List<TargetAndIntermediate>> configItems) {
        this.configItems = configItems;
    }

    public List<TargetAndIntermediate> getConfigForCell(Integer cellNumber) {
        return configItems.get(cellNumber);
    }

    private static List<TargetAndIntermediate> createConfigForItem(List<Integer> targets, List<Integer> intermediate) {
        List<TargetAndIntermediate> itemConfig = new ArrayList<>();
        for (int i = 0; i < targets.size(); i++) {
            itemConfig.add(new TargetAndIntermediate(targets.get(i), intermediate.get(i)));
        }
        return itemConfig;
    }
}
