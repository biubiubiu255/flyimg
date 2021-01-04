package com.flyimg.comm.enums;

public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),

    UNKNOW(5000, "未知错误"),

    /* HTTP状态码错误 */
    HTTP_ERROR_100(100, "1XX错误"),
    HTTP_ERROR_300(300, "3XX错误"),
    HTTP_ERROR_400(400, "4XX错误"),
    HTTP_ERROR_500(500, "5XX错误"),
    HTTP_NOT_FOUND(404, "访问地址不存在！"),

    /* 参数错误：10001-19999 */
    PARAM_IS_INVALID(10001, "参数无效"),
    PARAM_IS_BLANK(10002, "参数为空"),
    PARAM_TYPE_BIND_ERROR(10003, "参数类型错误"),
    PARAM_NOT_COMPLETE(10004, "参数缺失"),
    PARAM_METHOD_NOT_ALLOWED(10006,"不支持当前请求方法"),
    PARAM_METHOD_NOT_FOUND(10007,"方法不存在"),
    PARAM_VERIFY_CODE_EX(10008,"验证码校验异常，请重新获取后再试"),
    PARAM_VERIFY_CODE_ERROR(10009,"验证码不匹配"),
    PARAM_TICKET_NOT_FOUND(10010,"上传凭据不存在"),

    /* 用户错误：20001-29999*/
    USER_NOT_LOGGED_IN(20001, "用户未登录"),
    USER_USERNAME_NOT_EXIST(20002, "用户名不存在"),
    USER_USERNAME_EXIST(20003, "用户名已存在"),
    USER_PASS_ERROR(20004, "密码错误"),
    USER_NOT_EXIST(20005, "用户不存在"),
    USER_HAS_EXISTED(20006, "用户已存在"),
    USER_MEM_LESS(20007,"用户内存容量不足"),



    /* 业务错误：30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),


    /* 系统错误：40001-49999 */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),


    /* 数据错误：50001-599999 */
    RESULE_DATA_NONE(50001, "数据未找到"),
    DATA_IS_WRONG(50002, "数据有误"),
    DATA_ALREADY_EXISTED(50003, "数据已存在"),
    DATA_PARSE_ERROR(50004,"数据解析异常"),
    DATA_FILE_WRITE_ERROR(40002, "数据异常，文件写入失败"),



    /* 接口错误：60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),
    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),
    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),
    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),
    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),
    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),
    INTERFACE_TIMEOUT(60007, "调用接口响应超时"),


    /* 权限错误：70001-79999 */
    PERMISSION_NO_ACCESS(70001, "无访问权限，用户token校验失败"),
    PERMISSION_NO_TOKEN(70002, "未发现请求token，权限校验失败");



    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
