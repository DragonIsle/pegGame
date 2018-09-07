package com.ivanindustrial.finder;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionFinder {

    private static final String deadEnd = "No solution has been found!";
    private BoardConfig boardConfig;

    public SolutionFinder(BoardConfig boardConfig) {
        this.boardConfig = boardConfig;
    }

    public String getSolution(BoardState state) {
        String result = deadEnd;
        if (checkIsGameFinished(state)) {
            result = state.getStepHistory();
        }

        List<BoardState> nextStates = getNextStates(state);
        if (!nextStates.isEmpty()) {
            for (BoardState nextState : nextStates) {
                String solution = getSolution(nextState);
                if (!solution.equals(deadEnd)) {
                    result = solution;
                    break;
                }
            }
        }
        return result;
    }

    private boolean checkIsGameFinished(BoardState state) {
        return state.getCellsWithPeg().size() == 1 && state.getCellState(17);
    }

    private List<BoardState> getNextStates(BoardState state) {
        return state.getCellsWithPeg().stream()
                .map(cellWithPeg -> getNextStatesForCell(cellWithPeg, state, boardConfig.getConfigForCell(cellWithPeg)))
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    private boolean isStepPossible(Integer targetCell, Integer intermediateCell, BoardState state) {
        return !state.getCellState(targetCell) && state.getCellState(intermediateCell);
    }

    private List<BoardState> getNextStatesForCell(Integer cellWithPeg, BoardState parentState, HashMap<Integer, Integer> cellConfig) {
        return cellConfig.entrySet().stream()
                .filter(entry -> isStepPossible(entry.getKey(), entry.getValue(), parentState))
                .map(entry -> getNextState(parentState, Arrays.asList(cellWithPeg, entry.getKey(), entry.getValue())))
                .collect(Collectors.toList());
    }

    private BoardState getNextState(BoardState parentState, List<Integer> cellsToInverse) {
        BoardState nextState = parentState.getCopy(cellsToInverse.get(0) + "-" + cellsToInverse.get(1) + " ");
        for (Integer cell: cellsToInverse) {
            nextState.setCellState(cell, !parentState.getCellState(cell));
        }
        return nextState;
    }
}
