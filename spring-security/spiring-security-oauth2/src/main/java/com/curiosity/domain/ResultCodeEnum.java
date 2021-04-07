package com.curiosity.domain;

/**
 * 操作码标识通用类 枚举
 * <p>
 * 将返回错误内容定义
 * <p>
 * Created by abudu
 * on
 */
public enum ResultCodeEnum implements IErrorCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    SERVICE_UNKNOWN_ERROR(503, "未知异常"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    FILE_ERROR(600, "文件转换错误"),
    /*用户验证权限相关错误代码*/
    USER_AUTH_NO_TOKEN(90101, "token为空"),
    USER_AUTH_NO_ACCESS(90102, "无权限调用接口"),
    USER_AUTH_TOKEN_INVALID(90103, "token无效"),
    USER_AUTH_TOKEN_TTL(90104, "token过期"),
    /*用户登录验证相关错误码*/
    USER_INFO_NULL(90201, "操作员ID或密码不允许为空"),
    NO_USER(90202, "操作员不存在"),
    WRONG_USER_STATUS(90203, "操作员状态异常"),
    WRONGPWD(90204, "密码不正确"),
    CREATE_TOKEN_ERROR(90205, "生成token信息出错"),

    QUESTION_TEMPLATE_NO(90199,"模板编号不能为空"),
    QUESTION_TEMPLATE_NAME(90198,"问题名称不能为空"),
    QUESTION_TEMPLATE_TYPE(90197,"问题类型不能为空"),
    QUESTION_TEMPLATE_ITEM(90196,"问题选项内容不能为空"),
    QUESTION_TEMPLATE_ITEM_QNAME(90195,"问题选项内容名称不能为空"),
    QUESTION_TEMPLATE_ADDSUCCESS(90194,"问卷模板新增成功");





    private int code;
    private String message;

    private ResultCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
