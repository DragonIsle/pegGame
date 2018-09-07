package com.ivanindustrial.entities;

public class TargetAndIntermediate {

    private Integer targetCell;
    private Integer intermediateCell;

    public TargetAndIntermediate(Integer targetCell, Integer intermediateCell) {
        this.targetCell = targetCell;
        this.intermediateCell = intermediateCell;
    }

    public Integer getTargetCell() {
        return targetCell;
    }

    public void setTargetCell(Integer targetCell) {
        this.targetCell = targetCell;
    }

    public Integer getIntermediateCell() {
        return intermediateCell;
    }

    public void setIntermediateCell(Integer intermediateCell) {
        this.intermediateCell = intermediateCell;
    }
}
