package com.ivanindustrial.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ivan Kazimirov
 */
public class BoardState {

    private HashMap<Integer, Boolean> cells;
    private String stepHistory;

    private BoardState(HashMap<Integer, Boolean> cells, String stepHistory) {
        this.cells = cells;
        this.stepHistory = stepHistory;
    }

    public static BoardState createBoard(int cellCount, int emptyCell) {
        HashMap<Integer, Boolean> cells = new HashMap<>();
        for (int i = 1; i <=cellCount; i++) {
            cells.put(i, true);
        }
        cells.put(emptyCell, false);
        return new BoardState(cells, "");
    }

    public Boolean getCellState(Integer cellNumber) {
        return cells.get(cellNumber);
    }

    public void setCellState(Integer cellNumber, Boolean cellState) {
        cells.put(cellNumber, cellState);
    }

    public String getStepHistory() {
        return stepHistory;
    }

    public List<Integer> getCellsWithPeg() {
        List<Integer> cellsWithPeg = new LinkedList<>();
        for (Integer cell: cells.keySet()) {
            if (getCellState(cell)) {
                cellsWithPeg.add(cell);
            }
        }
        return cellsWithPeg;
    }

    public BoardState getCopy(String stepHistory) {
        return new BoardState((HashMap<Integer, Boolean>)cells.clone(), this.stepHistory + stepHistory);
    }
}
