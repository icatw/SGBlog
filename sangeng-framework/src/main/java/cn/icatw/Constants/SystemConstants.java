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
    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友联评论
     */
    public static final String LINK_COMMENT = "1";

}
