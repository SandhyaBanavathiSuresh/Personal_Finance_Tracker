package com.expense.tracker.controller;

import com.expense.tracker.dto.GraphDTO;
import com.expense.tracker.services.graph.GraphService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/graph")
@CrossOrigin("*")
public class GraphController {

    private final GraphService graphService;

    public ResponseEntity<GraphDTO> getGraphData(){
        return ResponseEntity.ok(graphService.getGraphData());
    }
}

