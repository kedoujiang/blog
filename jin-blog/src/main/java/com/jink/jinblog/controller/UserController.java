package com.jink.jinblog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author JINK
 * @version 1.0
 * @project jin-blog
 * @description
 * @date 2022/11/20 00:18:29
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理",description = "管理User接口信息")
public class UserController {


    @GetMapping("test")
    @Operation(summary = "test")
    public String test(){
        return "test";
    }
}
