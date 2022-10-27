package cn.icatw.enums;

/**
 * 响应枚举
 *
 * @author 76218
 */

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    LOGIN_EXPIRES(402, "登陆过期，请重新登陆"),
    SYSTEM_ERROR(500, "出现错误"),
    Parameter_ERROR(500, "参数有误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONENUMBER_EXIST(502, "手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505, "用户名或密码错误"),
    FILE_TYPE_ERROR(600, "只能上传png格式的图片！"),
    CONTENT_NOT_NULL(506, "评论不能为空"),
    ARGS_ERROR(507, "参数校验异常");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
