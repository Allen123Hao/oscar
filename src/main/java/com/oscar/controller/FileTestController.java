package com.oscar.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

/**
 * <code>FileTestController</code>
 *
 * @description:
 * @author: Hao Xueqiang(xueqiang.hao@tendcloud.com)
 * @creation: 2017/3/6
 * @version: 1.0
 */
@RestController
@RequestMapping("/filetest")
public class FileTestController {

    @RequestMapping("fileUpload")
    public Object fileUpload(){
        System.out.println("aaa");
        return new ModelAndView("filetest");
    }

    /*
     * 通过流的方式上传文件
     * @RequestParam("file") 将name=file控件得到的文件封装成CommonsMultipartFile 对象
     */
    @RequestMapping("fileUpload1")
    public String  fileUpload1(@RequestParam("file") CommonsMultipartFile file,@RequestParam("fileName") String name,@RequestParam("fileMD5") String fileMD5) throws IOException {
        //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        System.out.println("name:"+name);
        System.out.println("md5:"+fileMD5);
        System.out.println("fileName："+file.getOriginalFilename());

        try {
            //获取输出流
            OutputStream os=new FileOutputStream("E:\\TalkingData\\resource\\filetest\\"+new Date().getTime()+file.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "fileUpload1";
    }

    /*
     * 采用file.Transto 来保存上传的文件
     */
    @RequestMapping("fileUpload2")
    public String  fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());
        String path="E:\\TalkingData\\resource\\filetest\\"+new Date().getTime()+file.getOriginalFilename();

        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        file.transferTo(newFile);
        long  endTime=System.currentTimeMillis();
        System.out.println("方法二的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "fileUpload2";
    }

    /*
     *采用spring提供的上传文件的方法
     */
    @RequestMapping("fileUpload3")
    public String  fileUpload3(HttpServletRequest request) throws IllegalStateException, IOException
    {
        long  startTime=System.currentTimeMillis();
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request))
        {
            //将request变成多部分request
            MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
            //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();

            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null)
                {
                    String path="E:\\TalkingData\\resource\\filetest\\"+file.getOriginalFilename();
                    //上传
                    file.transferTo(new File(path));
                }

            }

        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "fileUpload2";
    }
}
