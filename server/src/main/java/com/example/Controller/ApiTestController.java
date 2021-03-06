package com.example.Controller;

import com.example.Model.RespEntity;
import com.example.Model.TestEndPoint;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
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

    // 返回整个图数据库
    @RequestMapping(value = "find_all", method = RequestMethod.GET)
    @ResponseBody
    public String FindAll(){
        String url = "http://neo4j:admin@localhost:7474/db/data/transaction/commit";
        CloseableHttpClient httpClient = HttpClients.custom().build();

        StringEntity s = new StringEntity("{\"statements\":[{ \"statement\":\"MATCH path = (n)-[r]->(m) RETURN path\", \"resultDataContents\":[\"graph\"]}]}","UTF-8");
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");


        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(s);
        httppost.addHeader("Content-Type", "application/json");
        httppost.addHeader("charset", "UTF-8");
        CloseableHttpResponse response = null;

        String respString = "";
        try {
            response = httpClient.execute(httppost);
            respString = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return respString;
    }

    // 上传文件 用于导入数据
    @PostMapping("/upload")
    @ResponseBody
    public RespEntity upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new RespEntity(RespEntity.RespCode.SUCCESS, "上传失败, 请重新选择文件");
        }

        String fileName = file.getOriginalFilename();
        String filePath = "/Users/bing/Desktop/github repo/CriminalMiner/tmp/";
        File dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            return new RespEntity(RespEntity.RespCode.SUCCESS, "上传成功");
        } catch (IOException e) {
            return new RespEntity(RespEntity.RespCode.SUCCESS, "上传失败:\n" + e.getMessage());
        }
    }
}