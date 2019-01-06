package com.baizhi.springb1.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.springb1.entity.Album;
import com.baizhi.springb1.entity.Article;
import com.baizhi.springb1.entity.User;
import com.baizhi.springb1.excp.CheckCodeException;
import com.baizhi.springb1.excp.LoginFailException;
import com.baizhi.springb1.excp.PhoneCheckCodeException;
import com.baizhi.springb1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/cmfz")
public class CMFZController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/first_page/{uid}/{type}/{subType}")
    public Object firstPage(@PathVariable(name = "uid") Integer uid,@PathVariable(name = "type") String type, @PathVariable(name = "subType") String subType){
        if (uid == null || type == null){
            Map<String,Object> map = new HashMap<>();
            map.put("404","找不到页面了");
            return map;
        } else {
            if (type.equals("all")) {
                Map<String, Object> map = new HashMap<>();
                map.put("banner", bannerService.findByFirstPage());
                map.put("album", albumService.findByFirstPage());
                map.put("article", chapterService.findByFirstPage());
                return map;
            } else if (type.equals("wen")) {
                Map<String, Object> map = new HashMap<>();
                map.put("album", albumService.findAll());
                return map;
            } else {
                if (subType != null) {
                    if (subType.equals("ssyj")) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("album", articleService.findByUId(uid));
                        return map;
                    } else {
                        Map<String, Object> map = new HashMap<>();
                        map.put("album", articleService.findByNotUId(uid));
                        return map;
                    }
                }else {
                    Map<String,Object> map = new HashMap<>();
                    return map;
                }
            }
        }

    }

    @RequestMapping("detail")
    public Article findOneArticle(Integer uid, Integer id){
        Article oneArticle = articleService.findOneArticle(uid, id);
        return oneArticle;
    }

    @RequestMapping("detail/wen")
    public Album findOneAlbum(Integer id, Integer uid){
        Album oneAlbum = albumService.findOneAlbum(id, uid);
        return oneAlbum;
    }
    @RequestMapping("/account/login/{phone}/{password}/code")
    public User login(@PathVariable(name = "phone") String phone,@PathVariable(name = "password") String password,@PathVariable(name = "code") String code, HttpSession session){
        String code1 = (String)session.getAttribute("code");
        if (!code.equalsIgnoreCase(code1)){
            throw new CheckCodeException("验证码错误");
        }else{
            User userOne = userService.findUserOne(phone, password);
            return userOne;
        }
    }

    @RequestMapping("/account/register/{phone}/{password}")
    public User register(@PathVariable(name = "phone") String phone,@PathVariable(name = "password") String password){
        User user = userService.addUser(phone, password);
        return user;
    }

    @RequestMapping("/account/modify")
    public User modify(User user){
        User user1 = userService.modifyUser(user);
        return user1;
    }

    @RequestMapping("/identify/obtain")
    public void obtain(String phone) throws Exception{
        Random random = new Random();
        String cod = "";
        for (int i = 0; i < 4; i++) {
             cod+=random.nextInt(9);
        }
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAIFBm4MggRQCE6";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "QvZuybTABR8Xmrkt2QH3cmex5Nqs2Q";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
        request.setPhoneNumbers("18790873167");
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("何腾飞");
        //必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
        request.setTemplateCode("SMS_141606919");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam("{code:"+cod+"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
        //请求成功
            Jedis jedis = new Jedis();
            jedis.set("code",cod);
            jedis.expire("code",60);
        }
    }

    @RequestMapping("/identify/check/{phone}/{code}")
    public Object check(@PathVariable("phone") String phone,@PathVariable("code") String code){
        if (phone == null || code == null){
            throw new LoginFailException("内部错误");
        } else {
            Jedis jedis = new Jedis();
            String code1 = jedis.get("code");
            if (!code1.equals(code)){
                throw new PhoneCheckCodeException("验证码错误");
            } else {
                Map<String,Object> map = new HashMap<>();
                map.put("result","success");
                return map;
            }
        }

    }
}
