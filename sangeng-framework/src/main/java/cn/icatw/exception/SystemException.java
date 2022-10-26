package cn.icatw.exception;

import cn.icatw.enums.AppHttpCodeEnum;

/**
 * 系统异常
 *
 * @author icatw
 * @date 2022/10/26
 */
public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
