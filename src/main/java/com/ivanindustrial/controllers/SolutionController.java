package com.ivanindustrial.controllers;

import com.ivanindustrial.config.BoardConfig;
import com.ivanindustrial.entities.BoardState;
import com.ivanindustrial.entities.BoardType;
import com.ivanindustrial.services.SolutionFinder;
import com.ivanindustrial.services.SolutionFinderService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class SolutionController {

    @Value("${board.config.path}")
    private String configsPath;

    private SolutionFinderService solutionFinderService;

    public SolutionController(SolutionFinderService solutionFinderService) {
        this.solutionFinderService = solutionFinderService;
    }

    @RequestMapping("/solution")
    public String getSolution(@RequestParam(defaultValue = "TRIANGLE") BoardType boardType) throws IOException {
        Resource resource = new ClassPathResource(configsPath + boardType.name().toLowerCase());
        return solutionFinderService.findSolution(new BoardState(boardType.getCellCount(), boardType.getEmptyCell()),
                new BoardConfig(resource.getFile()));
//        return new SolutionFinder(new BoardConfig(resource.getFile())).getSolution(new BoardState(boardType.getCellCount(), boardType.getEmptyCell()));
    }
}
