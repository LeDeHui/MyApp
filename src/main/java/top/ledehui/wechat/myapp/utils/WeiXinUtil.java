package top.ledehui.wechat.myapp.utils;/*
 * @author  ledehui
 * @data 2022/10/25)
 * @ApiNote
 */

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import top.ledehui.wechat.myapp.vo.wechat.menu.Button;
import top.ledehui.wechat.myapp.vo.wechat.menu.ClickButton;
import top.ledehui.wechat.myapp.vo.wechat.menu.Menu;
import top.ledehui.wechat.myapp.vo.wechat.menu.ViewButton;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeiXinUtil {
    /**
     * 组装菜单
     *
     * @return
     */

    public static Menu initMenu() {

        Menu menu = new Menu();

        ClickButton button11 = new ClickButton();

        button11.setName("了解杰瑞教育");

        button11.setType("click");

        button11.setKey("11");

        ClickButton button12 = new ClickButton();

        button12.setName("加入杰瑞教育");

        button12.setType("click");

        button12.setKey("12");

        ViewButton button21 = new ViewButton();

        button21.setName("杰瑞教育官网");

        button21.setType("view");

        button21.setUrl("http://www.jerehedu.com");

        ViewButton button22 = new ViewButton();

        button22.setName("杰瑞教育新闻网");

        button22.setType("view");

        button22.setUrl("http://www.jredu100.com");

        ClickButton button31 = new ClickButton();

        button31.setName("杰小瑞");

        button31.setType("click");

        button31.setKey("31");

        Button button1 = new Button();

        button1.setName("杰瑞教育"); //将11/12两个button作为二级菜单封装第一个一级菜单

        button1.setSub_button(new Button[]{button11, button12});

        Button button2 = new Button();

        button2.setName("相关网址"); //将21/22两个button作为二级菜单封装第二个二级菜单

        button2.setSub_button(new Button[]{button11, button12});

        menu.setButton(new Button[]{button1, button2, button31});// 将31Button直接作为一级菜单

        return menu;

    }

    //文本消息
    public static String resText(String FromUserName, String ToUserName, String text) {
        /*
        参数	是否必须	描述
ToUserName	是	接收方帐号（收到的OpenID）
FromUserName	是	开发者微信号
CreateTime	是	消息创建时间 （整型）
MsgType	是	消息类型，文本为text
Content	是	回复的消息内容（换行：在 content 中能够换行，微信客户端就支持换行显示）

         */
        long l = System.currentTimeMillis();
        String textMsg = "<xml>" +
                "<ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>" +
                "<FromUserName><![CDATA[" + ToUserName + "]]></FromUserName>" +
                "<CreateTime>" + l + "</CreateTime>" +
                "<MsgType><![CDATA[text]]></MsgType>" +
                "<Content><![CDATA[" + text + "]]></Content>" +
                "</xml>";
        return textMsg;
    }

    //图片消息
    public static String resImage(String FromUserName, String ToUserName, String text) {
        /*参数	是否必须	说明
ToUserName	是	接收方帐号（收到的OpenID）
FromUserName	是	开发者微信号
CreateTime	是	消息创建时间 （整型）
MsgType	是	消息类型，图片为image
MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id。
*/
        long l = System.currentTimeMillis();
        String textMsg = "<xml>" +
                "<ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>" +
                "<FromUserName><![CDATA[" + ToUserName + "]]></FromUserName>" +
                "<CreateTime>" + l + "</CreateTime>" +
                "<MsgType><![CDATA[image]]></MsgType>" +
                "  <Image>" +
                "  <MediaId><![CDATA[" + "vv6wqeF7ktIxPpHmG4nQqKJT2KIER6M56w9G2tdmNOXyJsl2aquS_fB1WIaCbZT1" + "]]></MediaId>" +
                " </Image> " +
                "</xml>";
        return textMsg;
    }

    public static String resVoice(String FromUserName, String ToUserName, String text) {

/**
 * 参数	是否必须	说明
 * ToUserName	是	接收方帐号（收到的OpenID）
 * FromUserName	是	开发者微信号
 * CreateTime	是	消息创建时间戳 （整型）
 * MsgType	是	消息类型，语音为voice
 * MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id
 */
        long l = System.currentTimeMillis();
        String textMsg = "<xml>" +
                "<ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>" +
                "<FromUserName><![CDATA[" + ToUserName + "]]></FromUserName>" +
                "<CreateTime>" + l + "</CreateTime>" +
                "<MsgType><![CDATA[voice]]></MsgType>" +
                "  <Voice>" +
                "  <MediaId><![CDATA[" + "vv6wqeF7ktIxPpHmG4nQqKJT2KIER6M56w9G2tdmNOXyJsl2aquS_fB1WIaCbZT1" + "]]></MediaId>" +
                " </Voice> " +
                "</xml>";
        return textMsg;
    }

    public static String resVideo(String FromUserName, String ToUserName, String text) {

/*
 参数	是否必须	说明
 ToUserName	是	接收方帐号（收到的OpenID）
 FromUserName	是	开发者微信号
 CreateTime	是	消息创建时间 （整型）
 MsgType	是	消息类型，视频为video
 MediaId	是	通过素材管理中的接口上传多媒体文件，得到的id
 Title	否	视频消息的标题
 Description	否	视频消息的描述
 */
        long l = System.currentTimeMillis();
        String textMsg = "<xml>" +
                "<ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>" +
                "<FromUserName><![CDATA[" + ToUserName + "]]></FromUserName>" +
                "<CreateTime>" + l + "</CreateTime>" +
                "<MsgType><![CDATA[video]]></MsgType>" +
                "  <Voice>" +
                "  <MediaId><![CDATA[" + "vv6wqeF7ktIxPpHmG4nQqKJT2KIER6M56w9G2tdmNOXyJsl2aquS_fB1WIaCbZT1" + "]]></MediaId>" +
                " <Title><![CDATA[title]]></Title>" +
                "    <Description><![CDATA[description]]></Description>" +
                " </Voice> " +
                "</xml>";
        return textMsg;
    }

    public static String resMusic(String FromUserName, String ToUserName, String text) {

/*
 参数	是否必须	说明
ToUserName	是	接收方帐号（收到的OpenID）
FromUserName	是	开发者微信号
CreateTime	是	消息创建时间 （整型）
MsgType	是	消息类型，音乐为music
Title	否	音乐标题
Description	否	音乐描述
MusicURL	否	音乐链接
HQMusicUrl	否	高质量音乐链接，WIFI环境优先使用该链接播放音乐
ThumbMediaId	是	缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
 */
        long l = System.currentTimeMillis();
        String textMsg = "<xml>" +
                "<ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>" +
                "<FromUserName><![CDATA[" + ToUserName + "]]></FromUserName>" +
                "<CreateTime>" + l + "</CreateTime>" +
                "<MsgType><![CDATA[music]]></MsgType>" +
                " <Music>" +
                "    <Title><![CDATA[TITLE]]></Title>" +
                "    <Description><![CDATA[DESCRIPTION]]></Description>" +
                "    <MusicUrl><![CDATA[MUSIC_Url]]></MusicUrl>" +
                "    <HQMusicUrl><![CDATA[HQ_MUSIC_Url]]></HQMusicUrl>" +
                "    <ThumbMediaId><![CDATA[media_id]]></ThumbMediaId>" +
                "  </Music>" +
                "</xml>";
        return textMsg;
    }

    public static String resNews(String FromUserName, String ToUserName, String text) {

/*
 参数	是否必须	说明
ToUserName	是	接收方帐号（收到的OpenID）
FromUserName	是	开发者微信号
CreateTime	是	消息创建时间 （整型）
MsgType	是	消息类型，图文为news
ArticleCount	是	图文消息个数；当用户发送文本、图片、语音、视频、图文、地理位置这六种消息时，开发者只能回复1条图文消息；其余场景最多可回复8条图文消息
Articles	是	图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数
Title	是	图文消息标题
Description	是	图文消息描述
PicUrl	是	图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
Url	是	点击图文消息跳转链接
 */
        long l = System.currentTimeMillis();
        String textMsg = "<xml>" +
                "<ToUserName><![CDATA[" + FromUserName + "]]></ToUserName>" +
                "<FromUserName><![CDATA[" + ToUserName + "]]></FromUserName>" +
                "<CreateTime>" + l + "</CreateTime>" +
                "<MsgType><![CDATA[news]]></MsgType>" +
                " <ArticleCount>1</ArticleCount>" +
                " <Articles>" +
                "    <item>" +
                "      <Title><![CDATA[" + "百度首页" + "]]></Title>" +
                "      <Description><![CDATA[" + "百度" + "]]></Description>" +
                "      <PicUrl><![CDATA["+"https://www.baidu.com/img/PCfb_5bf082d29588c07f842ccde3f97243ea.png"+"]]></PicUrl>" +
                "      <Url><![CDATA[" + "http://www.baidu.com" + "]]></Url>" +
                "    </item>" +
                "  </Articles>" +
                "</xml>";
        return textMsg;
    }

    // 利用dom4j中的类进行解析
    public static Map<String, String> parseRequest(InputStream is) {
        Map<String, String> map = new HashMap();
        // 1. 通过io流得到文档对象
        SAXReader saxReader = new SAXReader();
        Document document = null;
        try {
            document = saxReader.read(is);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        // 2.通过文档对象得到根节点对象
        Element root = document.getRootElement();
        // 3.通过根节点对象获取所有子节点对象
        List<Element> elements = root.elements();
        // 4.将所有节点放入map
        for (Element element : elements) {
            map.put(element.getName(), element.getStringValue());
        }
        //System.out.println("用户信息" + map);
        return map;
    }
}
