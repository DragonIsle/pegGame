package com.ivanindustrial.services;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;

/**
 * @author Ivan Kazimirov
 */
@RunWith(Parameterized.class)
public class SolutionFinderServiceTest {

    private BoardConfig initConfig;
    private Integer cellCount;
    private Integer emptyCell;
    private String expectedResult;
    private SolutionFinderService solutionFinderService;

    @Before
    public void initialize() {
        solutionFinderService = new SolutionFinderService();
    }

    public SolutionFinderServiceTest(BoardConfig initConfig, Integer cellCount, Integer emptyCell, String expectedResult) {
        this.initConfig = initConfig;
        this.cellCount = cellCount;
        this.emptyCell = emptyCell;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() throws IOException {
        Object[][] objects = new Object[][] {
                { BoardConfig.createConfigFromFile("C:/IdeaProjects/peg-game/src/main/resources/board.configs/triangle"),
                        15, 1, "4-1 6-4 1-6 7-2 10-3 12-5 13-6 2-9 3-10 15-6 6-13 14-12 11-13"}
        };
        return Arrays.asList(objects);
    }

    @Test
    public void testSolutionFinderService() {
        solutionFinderService.setBoardConfig(initConfig);
        assertEquals(expectedResult,
                solutionFinderService.getSolution(BoardState.createBoard(cellCount, emptyCell)).trim());
    }
}
