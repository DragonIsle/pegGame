package com.ivanindustrial;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;
import com.ivanindustrial.finder.SolutionFinder;

public class Main {

    private static BoardConfig config = BoardConfig.initEnglishBoardConfig();

    private static final String deadEnd = "No solution has been found!";

    public static void main(String[] args) {
//        System.out.println(getSolution(BoardState.createTriangleBoard()));
        System.out.println(getSolution(BoardState.createEnglishBoard()));
    }

    private static String getSolution(BoardState state) {
        if (checkIsGameFinished(state)) {
            return state.getStepHistory();
        }

        List<BoardState> nextStates = getNextStates(state);
        if (!nextStates.isEmpty()) {
            for (BoardState nextState : nextStates) {
                String solution = getSolution(nextState);
                if (!solution.equals(deadEnd)) {
                    return solution;
                }
            }
        }
        return deadEnd;
    }

    private static boolean checkIsGameFinished(BoardState state) {
        return state.getCellsWithPeg().size() == 1 && state.getCellState(17);
    }

    private static List<BoardState> getNextStates(BoardState state) {
        List<Integer> cellsWithPeg = state.getCellsWithPeg();
        List<BoardState> nextStates = new LinkedList<>();
        for (Integer cellWithPeg: cellsWithPeg) {
            nextStates.addAll(getNextStatesForCell(cellWithPeg, state, config.getConfigForCell(cellWithPeg)));
        }
        return nextStates;
    }

    private static boolean isStepPossible(Integer targetCell, Integer intermediateCell, BoardState state) {
        return !state.getCellState(targetCell) && state.getCellState(intermediateCell);
    }

    private static List<BoardState> getNextStatesForCell(Integer cellWithPeg, BoardState parentState, HashMap<Integer, Integer> cellConfig) {
        return cellConfig.entrySet().stream()
                .filter(entry -> isStepPossible(entry.getKey(), entry.getValue(), parentState))
                .map(entry -> getNextState(parentState, Arrays.asList(cellWithPeg, entry.getKey(), entry.getValue())))
                .collect(Collectors.toList());
    }

    private static BoardState getNextState(BoardState parentState, List<Integer> cellsToInverse) {
        BoardState nextState = parentState.getCopy(" " + cellsToInverse.get(0) + "-" + cellsToInverse.get(1));
        for (Integer cell: cellsToInverse) {
            nextState.setCellState(cell, !parentState.getCellState(cell));
        }
        return nextState;
    }
}
