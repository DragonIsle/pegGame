package com.ivanindustrial.services;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;
import com.ivanindustrial.entities.TargetAndIntermediate;

import java.util.Collection;
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
        if (checkIsGameFinished(state)) {
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

    private boolean checkIsGameFinished(BoardState state) {
        return state.getCellsWithPeg().size() == 1;
    }

    private List<BoardState> getNextStates(BoardState state, BoardConfig config) {
        return state.getCellsWithPeg().stream()
                .map(cellWithPeg -> getNextStatesForCell(cellWithPeg, state, config.getConfigForCell(cellWithPeg)))
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
