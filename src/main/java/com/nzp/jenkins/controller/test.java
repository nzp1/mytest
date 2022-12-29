package com.nzp.jenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nianzhipeng
 * @Title: 测试
 * @Package
 * @Description:
 * @date 2022/6/2522:11
 */
@RestController
public class test {
    @GetMapping("/test")
    public String test() {
        return "jenks";
    }
}
