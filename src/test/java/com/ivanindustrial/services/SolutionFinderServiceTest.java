package com.ivanindustrial.services;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ivanindustrial.entities.BoardType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @author Ivan Kazimirov
 */
@RunWith(Parameterized.class)
public class SolutionFinderServiceTest {

    private BoardType boardType;
    private String expectedResult;
    private SolutionFinderService solutionFinderService;

    private static String configsPath = "board/configs/";

    @Before
    public void initialize() {
        solutionFinderService = new SolutionFinderService();
    }

    public SolutionFinderServiceTest(BoardType boardType, String expectedResult) {
        this.boardType = boardType;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() throws IOException {
        List<Object[]> objects = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new ClassPathResource("testData").getFile()));
        while (reader.ready()) {
            String[] inputExpectedPair = reader.readLine().split(":");
            objects.add(new Object[] {
                    BoardType.valueOf(inputExpectedPair[0].toUpperCase()),
                    inputExpectedPair[1]
            });
        }
        return objects;
    }

    @Test
    public void testSolutionFinderService() throws IOException {
        Resource resource = new ClassPathResource(configsPath + boardType.name().toLowerCase());
        solutionFinderService.setBoardConfig(new BoardConfig(resource.getFile()));
        assertEquals(expectedResult, solutionFinderService.getSolution(
                new BoardState(boardType.getCellCount(), boardType.getEmptyCell())));
    }
}
