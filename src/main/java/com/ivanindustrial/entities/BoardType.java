package com.ivanindustrial.entities;

public enum BoardType {
    ENGLISH(33, 17), TRIANGLE(15, 1);

    private Integer cellCount;
    private Integer emptyCell;

    BoardType(Integer cellCount, Integer emptyCell) {
        this.cellCount = cellCount;
        this.emptyCell = emptyCell;
    }

    public Integer getCellCount() {
        return cellCount;
    }

    public Integer getEmptyCell() {
        return emptyCell;
    }
}
