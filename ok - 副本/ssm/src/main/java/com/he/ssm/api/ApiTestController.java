package com.he.ssm.api;

import com.he.ssm.bean.ResultT;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author itaem
 * date:2020-08-26 2020/8/26:9:48
 */
@Slf4j
@Api(tags = "测试")
@RestController
public class ApiTestController {

    @ApiOperation("测试1")
    @PostMapping("/api/test/get")
    public ResultT<Void> get() {
        return ResultT.success();
    }


}