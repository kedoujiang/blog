package com.jink.jinblog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("test/{id}")
    @Operation(summary = "test")
    public String test(@PathVariable("id") Integer id) {
        return "test" + id;
    }
}
