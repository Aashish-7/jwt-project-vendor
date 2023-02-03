package com.jwtproject.products.common.controller;

import com.jwtproject.products.common.service.SearchByKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SearchByKeywordController {

    SearchByKeywordService searchByKeywordService;

    @Autowired
    public SearchByKeywordController(SearchByKeywordService searchByKeywordService) {
        this.searchByKeywordService = searchByKeywordService;
    }

    @GetMapping("/searchKeyword")
    public Map<String, Object> searchKeyword(@RequestParam String keyword, @RequestParam int page, @RequestParam int size){
        return searchByKeywordService.searchKeyword(keyword, page, size);
    }
}
