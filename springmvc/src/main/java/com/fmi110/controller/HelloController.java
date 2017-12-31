package com.fmi110.controller;

import com.fmi110.bean.User;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
public class HelloController {

    @InitBinder("user")
    public void initBinder(WebDataBinder binder){
        System.out.println("=========initBinder========");
        binder.setFieldDefaultPrefix("t.");
    }
//
    @InitBinder("t")
    public void initBinder2(WebDataBinder binder){
        System.out.println("=========initBinder2========");
        binder.setFieldDefaultPrefix("user.");
    }

    @RequestMapping("test")
    @ResponseBody
    public User test(@ModelAttribute("user") User user, @ModelAttribute("t") User u2){
        System.out.println(user);
        System.out.println(u2);
//        System.out.println(u3);
        return user;
    }

    /**
     * 文件上传功能
     * @param request
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(HttpServletRequest request,

                             @RequestParam("file")MultipartFile file) throws IOException {

        if(!file.isEmpty()){ // 上传了文件
            // 将图片存到工程目录下的 images 目录
            String path = "D:\\xxxx";
            System.out.println("path : "+path);

            String fileName = file.getOriginalFilename();
            File   desFile  = new File(path, fileName);

            if(!desFile.getParentFile().exists()){
                desFile.getParentFile().mkdirs(); // 文件夹不存在时创建文件夹
            }

            file.transferTo(desFile);
        }
        System.out.println(file);
        return "success";
    }

    /**
     * 文件下载功能
     * @return
     * @throws Exception
     */
    @RequestMapping("download")
    public ResponseEntity<byte[]> download() throws Exception {
        // 这里把下载的文件写死了,仅作演示
        File file = new File("/Users/huangyunning/Downloads/Spring MVC 入门.md");
        // 解决下载中文文件名显示异常的问题
        String filename = new String("Spring MVC 入门.md".getBytes(),"iso-8859-1");
        // 设置响应头信息
        HttpHeaders headers = new HttpHeaders();
        // 通知浏览器以下载的方式打开文件
        headers.setContentDispositionFormData("attachement",filename);
        // 设置响应为二进制流(最常见的文件下载方式)
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);  // application/octet-stream

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

    @RequestMapping("exception")
    @ResponseBody
    public String  testEx(){
        throw new RuntimeException("测试异常处理");
//        return "抛异常测试";
    }

//    /**
//     * 处理内部异常
//     * @param e
//     * @return
//     */
//    @ExceptionHandler
//    public String handleException(Exception e){
//        System.out.println(this +" handleException()");
//        return "error.jsp";
//    }
}
