package com.expense.tracker.services.graph;

import com.expense.tracker.dto.GraphDTO;
import com.expense.tracker.dto.StatisticDTO;

public interface GraphService {

    GraphDTO getGraphData();

    StatisticDTO getStatisticData();
}
