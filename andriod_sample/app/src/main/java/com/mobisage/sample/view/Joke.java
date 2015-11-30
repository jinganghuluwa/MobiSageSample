
package com.mobisage.sample.view;

public class Joke {

    public Joke(String title, String content, String logo, String image) {
        this.mTitle = title;
        this.mContent = content;
        this.mLogo = logo;
        this.mImage = image;
    }

    private String mTitle;
    private String mContent;
    private String mLogo;
    private String mImage;

    public String getImage() {
        return mImage;
    }

    public String getLogo() {
        return mLogo;
    }

    public void setLogo(String logo) {
        mLogo = logo;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public static final String[] NAMES = {
            "命里缺米", "喜肥一坨坨", "好色仙人123", "鞋子里有沙子",
            "yy木头"
    };
    public static final String[] CONTENTS = {
            "小明跟小华到动物园玩，进门时，小明指着小华对看门人说：“看清楚喔！等会儿出来，别说我偷了你们的猴子！” ",
            "有个人第一次在集市上卖冰棍，不好意思叫卖，旁边有一个人正高声喊：“卖冰棍”，他只好喊道：“我也是。”",
            "父亲对女儿的男友严厉地说：“你每天只带我女儿看电影，就不能做点别的事？”年轻人又惊又喜：“您是说可以做其它的事儿了吗？”",
            "学了点国语的老外。早晨和女秘书打招呼“你吗好？”小姐瞪了他一眼，他一楞，马上又对她说：“妈，你好！”",
            "有两只小鸟看见一个猎人正在瞄准它们，一只说，你保护现场我去叫警察！",
    };

    public static final String[] LOGOS = {
            "http://pic.qiushibaike.com/system/avtnew/1458/14588028/medium/20140808161925.jpg",
            "http://pic.qiushibaike.com/system/avtnew/872/8723279/medium/20140322180009.jpg",
            "http://pic.qiushibaike.com/system/avtnew/1571/15715835/medium/20140507062511.jpg",
            "http://pic.qiushibaike.com/system/avtnew/1857/18572231/medium/20140731102727.jpg",
            "http://pic.qiushibaike.com/system/avtnew/1697/16976039/medium/20140901132119.jpg"
    };
    public static final String[] IMAGES = {
            "http://pic.qiushibaike.com/system/pictures/8704/87044635/medium/app87044635.jpg",
            "http://pic.qiushibaike.com/system/pictures/8704/87044395/medium/app87044395.jpg",
            "http://pic.qiushibaike.com/system/pictures/8701/87018495/medium/app87018495.jpg",
            "http://pic.qiushibaike.com/system/pictures/8704/87046579/medium/app87046579.jpg",
            "http://pic.qiushibaike.com/system/pictures/8704/87045393/medium/app87045393.jpg",
    };
}
