package cn.icatw.Constants;

/**
 * 系统常量
 *
 * @author icatw
 * @date 2022/10/25
 */
public class SystemConstants {
    /**
     * 文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     * 文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;
    /**
     * 文章是正常分布状态
     */
    public static final int COMMENT_ROOT_ID = -1;
    /**
     * 启用状态常量
     */
    public static final String STATUS_NORMAL = "0";
    /**
     * 友联审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";
    /**
     * redis的登陆用户key
     */
    public static final String LOGIN_KEY = "bloglogin:";
    public static final String ADMIN_LOGIN_KEY = "adminbloglogin:";
    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友联评论
     */
    public static final String LINK_COMMENT = "1";
    /**
     * redis浏览量key
     */
    public static final String VIEW_COUNT_KEY = "article:viewCount";

    public static final String MENU = "C";
    public static final String BUTTON = "F";
    public static final String ADMIN = "1";
}
