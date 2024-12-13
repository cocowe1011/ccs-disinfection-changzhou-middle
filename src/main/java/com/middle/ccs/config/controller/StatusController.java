package com.middle.ccs.config.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @classDesc: 控制器:(CssConfig)
 * @author: makejava
 * @date: 2023-07-04 15:31:37
 * @copyright 众阳健康
 */
@RestController
@RequestMapping("/status")
public class StatusController {

    @RequestMapping("/check")
    public String check () {
        return "OK";
    }
}
