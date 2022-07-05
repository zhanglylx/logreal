package com.alibaba.logreal.common.result;

/**
 * @author zly
 * @version 1.0
 * @date 2021/4/10 22:15
 */
public enum  HttpCodeEnum {
    OK(0, "操作成功"),
    INVALID_REQUEST(400, "参数错误"),
    UNAUTHORIZED(401, "没有权限"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    NOT_ACCEPTABLE(406, "请求的格式不正确"),
    GONE(410, "数据被删除"),
    UNPROCESABLE_ENTITY(422, "参数验证错误"),
    INTERNAL_SERVER_ERROR(500, "服务器发生错误"),
    UN_KNOW_ERROR(500, "未知错误"),
    FAIL(1, "操作失败"),
    FAST_FAIL(-1, "断路器快速失败"),
    VERIFICATION_FAILED(1001, "业务逻辑验证未通过"),
    IOS_AD_NO_REPORT(2001, "iOS点击数据不上报到供应商"),
    IOS_AD_STOP(2002, "iOS供应商停止投放"),
    AUTH_EXPIRED(3000, "认证到期"),
    TOKEN_ERR(3001, "token无效");

    private final int code;
    private final String message;

    private HttpCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }
}
