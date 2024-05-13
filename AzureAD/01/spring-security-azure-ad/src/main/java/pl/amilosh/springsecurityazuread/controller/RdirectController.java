package pl.amilosh.springsecurityazuread.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redirect")
public class RdirectController {

    @GetMapping
    public String get(@RequestParam(required = false) String code) {
        return "/redirect get()";
    }
}
