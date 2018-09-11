package com.ivanindustrial.controllers;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ivanindustrial.entities.BoardType;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

/**
 * @author Ivan Kazimirov
 */
@RunWith(Parameterized.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SolutionControllerTest {

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE = new SpringClassRule();

    @Rule
    public final SpringMethodRule SPRING_METHOD_RULE = new SpringMethodRule();

    @Autowired
    private SolutionController controller;

    private BoardType boardType;
    private String expectedResult;

    public SolutionControllerTest(BoardType boardType, String expectedResult) {
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
    public void testSolutionController() throws IOException {
        assertEquals(expectedResult, controller.getSolution(boardType));
    }
}
