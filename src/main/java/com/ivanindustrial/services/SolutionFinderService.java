package com.ivanindustrial.services;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SolutionFinderService {

    @Value( "${deadend}" )
    private String deadEnd = "";

    public String findSolution(BoardState state, BoardConfig config) {
        String result = deadEnd;
        if (checkIsOnePegLeft(state)) {
            result = state.getStepHistory().trim();
        } else {
            List<BoardState> nextStates = getNextStates(state, config);
            if (!nextStates.isEmpty()) {
                for (BoardState nextState : nextStates) {
                    String solution = findSolution(nextState, config);
                    if (!solution.equals(deadEnd)) {
                        result = solution;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private boolean checkIsOnePegLeft(BoardState state) {
        return state.getCellsWithPeg().size() == 1;
    }

    private List<BoardState> getNextStates(BoardState state, BoardConfig config) {
        return state.getCellsWithPeg().stream()
                .map(cellWithPeg -> getNextStatesForCell(cellWithPeg, state, config.getCellConfig(cellWithPeg)))
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    private boolean isStepPossible(Integer targetCell, Integer intermediateCell, BoardState state) {
        return !state.getCellState(targetCell) && state.getCellState(intermediateCell);
    }

    private List<BoardState> getNextStatesForCell(Integer cellWithPeg, BoardState parentState, HashMap<Integer, Integer> cellConfig) {
        return cellConfig.entrySet().stream()
                .filter(entry -> isStepPossible(entry.getKey(), entry.getValue(), parentState))
                .map(entry -> getNextState(parentState, cellWithPeg, entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    private BoardState getNextState(BoardState parentState, Integer cellWithPeg, Integer targetCell, Integer intermediateCell) {
        BoardState nextState = parentState.makeStep(cellWithPeg + "-" + targetCell + " ");
        nextState.setCellState(cellWithPeg, false);
        nextState.setCellState(targetCell, true);
        nextState.setCellState(intermediateCell, false);
        return nextState;
    }
}
