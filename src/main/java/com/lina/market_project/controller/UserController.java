package com.lina.market_project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lina.market_project.service.impl.UserServiceImpl;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.web.bind.annotation.*;
import com.lina.market_project.pojo.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 白粥
 * @since 2021-07-16
 */
@MultipartConfig  //使用MultipartConfig注解标注改servlet能够接受文件上传的请求
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserServiceImpl userService1;

    @Resource
    HttpServletRequest request;

    @PostMapping("loginByUsername")
    public Result<?> findByUsernamePassword(@RequestBody User user){
        System.out.println(user);

        QueryWrapper<User> wrapper=new QueryWrapper<>();

        wrapper.eq("username",user.getUsername())
                .eq("password",user.getPassword());

        User one = userService1.getOne(wrapper);

        if(one!=null){
            return Result.success(one);
        }else {
            QueryWrapper<User> wrapper1=new QueryWrapper<>();
            wrapper1.eq("email",user.getUsername())
                    .eq("password",user.getPassword());

            User user1=userService1.getOne(wrapper1);

            if(user1!=null){
                return Result.success(user1);
            }else {
                return Result.error();
            }
        }
    }

    @GetMapping("getUserByUserId")
    public Result<?> getUserByUserId(@RequestParam int userId){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return Result.success(userService1.getOne(queryWrapper));
    }

    /**
     * 查找用户名
     * @return
     */
    @GetMapping("findUserName")
    public Result<?> findUserName(@RequestParam String UserName){
        QueryWrapper<User> wrapper=new QueryWrapper<>();

        wrapper.eq("username",UserName);

        User one = userService1.getOne(wrapper);

        if(one!=null){
            return Result.error();
        }else {
            return Result.success();
        }
    }

    @GetMapping
    public String hello(){
        return "hello world";
    }

    @GetMapping("sendEmail")
    public Result<?> sendEmail(@RequestParam String QQEmail){
        //查找是否已经注册
        QueryWrapper<User> wrapper=new QueryWrapper<>();

        wrapper.eq("email",QQEmail);

        User one = userService1.getOne(wrapper);


        if(one!=null){
            //产生4个随机验证码，12Ey
            String checkCode = getCheckCode();

            //------------------发送验证码--------------
            try {
                HtmlEmail email = new HtmlEmail();//不用更改
                email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
                email.setCharset("UTF-8");
                email.setAuthentication("2923904910@qq.com", "sphujplfnxupdeec");//此处填写邮箱地址和客户端授权码
                email.setFrom("2923904910@qq.com", "唯品会");//此处填邮箱地址和用户名,用户名可以任意填写
                email.setSSLCheckServerIdentity(true);
                email.addTo(QQEmail);// 收件地址
                email.setSubject("验证码");//此处填写邮件名，邮件名可任意填写
                email.setMsg("尊敬的用户您好,本次的验证码是:" + checkCode);//此处填写邮件内容

                email.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }

            HttpSession session=request.getSession();

            session.setAttribute("user",one);

            HashMap<String,Object> map=new HashMap<>();
            map.put("user",one);
            map.put("verifyCode",checkCode);

            return Result.success(map);
        }else {
            return Result.error();
        }
    }

    @GetMapping("sendEmailInRegister")
    public Result<?> sendEmailInRegister(@RequestParam String QQEmail){
        //查找是否已经注册
        QueryWrapper<User> wrapper=new QueryWrapper<>();

        wrapper.eq("email",QQEmail);

        User one = userService1.getOne(wrapper);


        if(one==null){
            //产生六个随机验证码
            String checkCode = getCheckCode();

            //------------------发送验证码--------------
            try {
                HtmlEmail email = new HtmlEmail();//不用更改
                email.setHostName("smtp.qq.com");//需要修改，126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com
                email.setCharset("UTF-8");
                email.setAuthentication("2923904910@qq.com", "sphujplfnxupdeec");//此处填写邮箱地址和客户端授权码
                email.setFrom("2923904910@qq.com", "唯品会");//此处填邮箱地址和用户名,用户名可以任意填写
                email.setSSLCheckServerIdentity(true);
                email.addTo(QQEmail);// 收件地址
                email.setSubject("验证码");//此处填写邮件名，邮件名可任意填写
                email.setMsg("尊敬的用户您好,本次的验证码是:" + checkCode);//此处填写邮件内容

                email.send();
            } catch (EmailException e) {
                e.printStackTrace();
            }

            HashMap<String,Object> map=new HashMap<>();
            map.put("verifyCode",checkCode);
            return Result.success(map);
        }else {
            return Result.error();
        }
    }

    @PostMapping("add")
    public Result<?> add(@RequestBody User user){
        boolean flag=userService1.save(user);
        return Result.success(flag);
    }

    @PostMapping("update")
    public Result<?> update(@RequestBody User user){
        QueryWrapper<User> wrapper=new QueryWrapper<>();

        wrapper.eq("user_id",user.getUserId());
        userService1.update(user,wrapper);

        return Result.success(user);
    }

    @GetMapping("modifyPassword")
    public Result<?> modifyPassword(@RequestParam String password,@RequestParam Integer id){
        System.out.println(password);
        System.out.println(id);
        return userService1.modifyPassword(password,id);
    }

    @PostMapping("uploadImage")
    public Result<?> modifyImage(@RequestParam Integer id, @RequestParam MultipartFile image) throws IOException {

        //获取上传文件的文件名
        String fileName = image.getOriginalFilename();

        System.out.println(image);
        System.out.println(fileName);

        String serverpath="C:\\Users\\lina\\IdeaProjects\\market_project\\market_bsfront\\src\\assets\\img";

        InputStream in=image.getInputStream();
        FileOutputStream fos=new FileOutputStream(serverpath+'\\'+fileName);

        byte[] bytes=new byte[1024];
        int length=0;
        while ((length=in.read(bytes))!=-1){
            fos.write(bytes,0,length);
        }
        fos.close();
        in.close();

        return userService1.modifyImage(fileName,id);
    }


    /**
     * 自动登录
     * @return
     */
    @GetMapping("/autoLogin")
    public Result<?> AutoLogin(){
        HttpSession httpSession=request.getSession();
        User user= (User) httpSession.getAttribute("user");

        System.out.println((User) httpSession.getAttribute("user"));

        if(user!=null){
            return Result.success(user);
        }else {
            return Result.error();
        }
    }

    /**
     * 退出登录
     */
    @GetMapping("/cancelLogin")
    public Result<?> CancelLogin(){
        HttpSession session=request.getSession();
        session.removeAttribute("user");

        return Result.success();
    }

    /**
     * 产生6位随机字符串
     */
    private String getCheckCode() {
        String base = "0123456789";
        int size = base.length();
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=6;i++){
            //产生0到size-1的随机值
            int index = r.nextInt(size);
            //在base字符串中获取下标为index的字符
            char c = base.charAt(index);
            //将c放入到StringBuffer中去
            sb.append(c);
        }
        return sb.toString();
    }
}

