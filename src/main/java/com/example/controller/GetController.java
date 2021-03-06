package com.example.controller;

import com.example.dto.LoginDto;
import com.example.dto.PostRequestDto;
import com.example.dto.RequestDto;
import com.example.service.ExcelService;
import com.example.service.GetService;
import com.example.service.MovieService;
import com.example.service.PostService;
import com.example.utils.Response.ResBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class GetController {
    @Autowired
    MovieService movieService;
    @Autowired
    GetService getService;
    @Autowired
    PostService postService;
    @Autowired
    ExcelService excelService;

    @RequestMapping(value = "/getMovie")
    public void myapitest() throws Exception {
        movieService.getMovie();
    }

    @RequestMapping(value = "/getData",method = RequestMethod.POST)
    public void get(@RequestBody RequestDto requestDto) throws Exception {
        getService.get(requestDto);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(@RequestBody LoginDto loginDto){

    }

    @RequestMapping(value = "/postData",method = RequestMethod.POST)
    public ResBody post(@RequestBody PostRequestDto postRequestDto) {
       String exportKey = postService.post(postRequestDto);
        return ResBody.buildSuccessResBody(exportKey);
    }

    @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
    public ResBody exportExcel(@RequestParam("key") String key) throws Exception {
        Optional.ofNullable(key).orElseThrow(()->new Exception("key不能为空"));
        excelService.exportExcel(key);
        return ResBody.buildSuccessResBody();
    }
}
