package com.ivanindustrial;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;
import com.ivanindustrial.finder.SolutionFinder;

import java.util.Date;

public class Application {

    public static void main(String[] args) {
        Date init = new Date();
        SolutionFinder finder = new SolutionFinder(BoardConfig.initTriangleBoardConfig());
        System.out.println(finder.getSolution(BoardState.createTriangleBoard()));
        System.out.println(new Date().getTime() - init.getTime());
    }
}
