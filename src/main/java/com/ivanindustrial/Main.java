package com.ivanindustrial;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;
import com.ivanindustrial.finder.SolutionFinder;

import java.io.IOException;
import java.util.Date;

public class Main {

    // TODO: make file path relative
    private static String filePath = "/home/saul/IdeaProjects/pegGame/src/main/resources/board.configs/";

    public static void main(String[] args) throws IOException {
        Date init = new Date();
        SolutionFinder finder = new SolutionFinder(BoardConfig.createConfigFromFile(filePath + "triangle"));
        System.out.println(finder.getSolution(BoardState.createBoard(15, 1)));
        System.out.println(new Date().getTime() - init.getTime());
    }
}
