package com.example.Controller;

import com.example.Model.RespEntity;
import com.example.Model.TestEndPoint;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("api")
public class ApiTestController {

    // 后端向前端传递数据
    @RequestMapping(value = "test", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
    public RespEntity apiTest(HttpServletResponse rsp) {
        TestEndPoint TestEndPoint = new TestEndPoint("zhub",new Date(System.currentTimeMillis()),1);
        return new RespEntity(RespEntity.RespCode.SUCCESS, TestEndPoint);
    }

    // 前端向后端传递数据
    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ResponseBody
    public RespEntity test(@RequestBody TestEndPoint test){
        if(test==null)
            return new RespEntity(RespEntity.RespCode.WARN, test);
        test.setDatetime(new Date(System.currentTimeMillis()));
        test.setFlag(test.getFlag()+1);
        return new RespEntity(RespEntity.RespCode.SUCCESS, test);
    }
}