package com.process.old.service.impl;

import com.process.old.service.LineColumnService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LineColumnServiceImpl implements LineColumnService {

    @Override
    public List<String> getItems() {
        return Arrays.asList("Item 1", "Item 2", "Item 3");
    }
}


