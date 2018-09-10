package com.ivanindustrial.controllers;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;
import com.ivanindustrial.entities.BoardType;
import com.ivanindustrial.services.SolutionFinder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SolutionController {

    @Value( "${config.path}" )
    private String configFilesPath;

    @RequestMapping("/solution")
    public String getSolution(@RequestParam(defaultValue = "TRIANGLE") BoardType boardType) throws IOException {
        SolutionFinder finder = new SolutionFinder(
                BoardConfig.createConfigFromFile(configFilesPath + boardType.name().toLowerCase()));
        return finder.getSolution(getBoardStateByType(boardType));
    }

    private BoardState getBoardStateByType(BoardType type) {
        BoardState state;
        switch (type) {
            case ENGLISH:
                state = BoardState.createBoard(33, 17);
                break;
            default:
                state = BoardState.createBoard(15, 1);
                break;
        }
        return state;
    }
}
