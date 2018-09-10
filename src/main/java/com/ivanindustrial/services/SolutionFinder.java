package com.ivanindustrial.services;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;
import com.ivanindustrial.entities.TargetAndIntermediate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionFinder {

    private static final String deadEnd = "No solution has been found!";
    private static final int pegsCountToFinishGame = 1;

    private BoardConfig boardConfig;

    public SolutionFinder(BoardConfig boardConfig) {
        this.boardConfig = boardConfig;
    }

    public String getSolution(BoardState state) {
        String result = deadEnd;
        if (checkIsGameFinished(state)) {
            result = state.getStepHistory();
        } else {
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
        }
        return result;
    }

    private boolean checkIsGameFinished(BoardState state) {
        return state.getCellsWithPeg().size() == pegsCountToFinishGame;
    }

    private List<BoardState> getNextStates(BoardState state) {
        return state.getCellsWithPeg().stream()
                .map(cellWithPeg -> getNextStatesForCell(cellWithPeg, state, boardConfig.getConfigForCell(cellWithPeg)))
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    private boolean isStepPossible(Integer targetCell, Integer intermediateCell, BoardState state) {
        return !state.getCellState(targetCell) && state.getCellState(intermediateCell);
    }

    private List<BoardState> getNextStatesForCell(Integer cellWithPeg,
                                                  BoardState parentState, List<TargetAndIntermediate> cellConfig) {
        return cellConfig.stream()
                .filter(tai -> isStepPossible(tai.getTargetCell(), tai.getIntermediateCell(), parentState))
                .map(tai -> getNextState(parentState, cellWithPeg, tai))
                .collect(Collectors.toList());
    }

    private BoardState getNextState(BoardState parentState, Integer cellWithPeg, TargetAndIntermediate tai) {
        BoardState nextState = parentState.getCopy(cellWithPeg + "-" + tai.getTargetCell() + " ");
        nextState.setCellState(cellWithPeg, false);
        nextState.setCellState(tai.getTargetCell(), true);
        nextState.setCellState(tai.getIntermediateCell(), false);
        return nextState;
    }
}
