package com.huayun.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hello")
@Api(tags = "用户管理相关接口")
public class HelloController {
    @GetMapping("/hell01")
    @ApiOperation("hello的接口")
    public String hello1() {
        return "hello1";
    }

    @GetMapping("/hell02")
    public String hello2() {
        return "hello2";
    }
}
