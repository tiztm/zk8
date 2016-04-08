package com.utils;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @ClassName: Sendmail
 * @Description: 发送Email
 * @author: 孤傲苍狼
 * @date: 2015-1-12 下午9:42:56
 *
 */
public class Sendmail {

    final static String mailhost = "smtp.189.cn";


    final static String sendaddress = "18066086334@189.cn";


    final static String passw = "651633";

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        sendMail("newTestZ航","我是张" );
    }

    public static void sendMail(String title, String content) throws Exception
    {
        sendMail( title,  content,"nils005@163.com");
    }

     public static void sendMail(String title, String content, String mailAddr) throws Exception {
        Properties prop = new Properties();
        prop.setProperty("mail.host", mailhost);
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(prop);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(false);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。

        ts.connect(mailhost, sendaddress, passw);
        //4、创建邮件
        Message message = createSimpleMail(session, title, content,mailAddr);
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     * @Anthor:孤傲苍狼
     *
     * @param session
     * @param mailAddr
     * @return
     * @throws Exception
     */
    public static MimeMessage createSimpleMail(Session session, String title, String content, String mailAddr)
            throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("zk8<"+sendaddress+">"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mailAddr));

        System.setProperty("mail.mime.charset","UTF-8");
        //邮件的标题
        message.setSubject(title);
        //邮件的文本内容
        message.setContent(content, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
}