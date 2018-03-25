package com.controller.manageController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by Tellyes_worker on 2017/12/29/0029.
 */
public class ValidationCode extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        //获得验证码集合的长度
        int charsLength = codeChars.length();
        //下面3条记录是关闭客户端浏览器的缓冲区
        //这3条语句都可以关闭浏览器的缓冲区，但是由于浏览器的版本不同，对这3条语句的支持也不同
        //因此，为了保险起见，同时使用这3条语句来关闭浏览器的缓冲区
        resp.setHeader("ragma","No-cache");
        resp.setHeader("Cache-Control","no-cache");
        resp.setDateHeader("Expires",0);

        int width = 90, height = 30;
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();//获得用于输出文字的Graphics对象
        Random random = new Random();
        g.setColor(getRandomColor(180,250));
        g.fillRect(0,0,width,height);
        g.setFont(new Font("Times new Roman",Font.ITALIC,height));
        g.setColor(getRandomColor(120,180));
        //用户保存最后随机生成的验证码
        StringBuilder validationCode = new StringBuilder();
        //验证码的随机字体
        String[] fontNames = {"Times new Roman","Book antiqua","Arial"};
        //随机生成4个验证码
        for(int i = 0;i<4;i++){
            //随机设置当前验证码的字符的字体
            g.setFont(new Font(fontNames[random.nextInt(3)],Font.ITALIC,height));
            //随机获得当前验证码的字符
            char codeChar = codeChars.charAt(random.nextInt(charsLength));
            validationCode.append(codeChar);
            //随机设置当前验证码字符的颜色
            g.setColor(getRandomColor(10,100));
            //在图形上输出验证码字符，x和y都是随机生成的
            g.drawString(String.valueOf(codeChar),16*i + random.nextInt(7),height-random.nextInt(6));
        }

        //获得HttpSession对象
        HttpSession session = req.getSession();
        //设置session对象5分钟失效
        session.setMaxInactiveInterval(5*60);
        //将验证码保存在session对象中,key为validation_code
        session.setAttribute("validation_code",validationCode.toString());
        //关闭Graphics对象
        g.dispose();
        OutputStream outs = resp.getOutputStream();
        ImageIO.write(image,"JPEG",outs);
    }
    @Override
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        doGet(req,resp);
    }

    //图形验证码的字符集，系统将随机从这个字符串中选择一些字符作为验证码
    private static String codeChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    //返回一个随机颜色
    private static Color getRandomColor(int minColor,int maxColor){
        Random random = new Random();
        if(minColor>255){
            minColor = 255;
        }
        if(maxColor>255){
            maxColor = 255;
        }

        //获得r的随机颜色值
        int red = minColor + random.nextInt(maxColor - minColor);
        //g
        int green = minColor + random.nextInt(maxColor - minColor);
        //b
        int blue = minColor + random.nextInt(maxColor -minColor);
        return new Color(red,green,blue);
    }
}
