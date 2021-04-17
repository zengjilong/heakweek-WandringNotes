package com.travelsnotes.pojo;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ResultCodeEnum {
    SUCCESS(true, 200, "成功"),
    UNKNOW_REASON(false, 20001, "未知错误"),
    BAD_SQL_GRAMMER(false, 21001, "sql语法错误"),
    JSON_PARSE_ERROR(false, 21002, "json解析异常"),
    PARAM_ERROR(false, 21003, "参数不正确"),
    FILE_UPLOAD_ERROR(false, 21004, "文件上传错误"),
    EXCEL_DATA_IMPORT_ERROR(false, 21005, "Excel数据导入错误"),
    ERROR_AUTH_CODE(false, 21006, "验证码错误!"),
    ERROR_NOT_EXISTS_USER(false, 21007, "用户不存在!"),
    ERROR_IS_EXISTS_USER(false, 21007, "用户已存在!"),

    ERROR_PASSWORD(false, 21008, "密码错误,请重新输入!"),
    FAIL_REGISTER(false, 21009, "注册失败,用户名已存在!"),
    FAIL_UPDATE(false, 21009, "修改失败!"),
    FAIL_TOKENNOFINDED(false, 21010, "token未找到!"),
    FAIL_USERNOTNULL(false, 21011, "用户名不能为空!"),
    FAIL_PASSWORDNOTNULL(false, 21012, "密码不能为空!"),
    FAIL_TOKENNONULL(false, 21013, "token不能为空!"),
    FAIL_AVATERNONULL(false, 21013, "头像不能为空!"),
    SUCCESS_LOGIN(true, 200, "登陆成功!"),
    SUCCESS_REGISTER(true, 200, "注册成功!"),
    SUCCESS_USABLE(true, 200, "可以使用该用户名!"),
    FAIL_SAVEFILE(true, 201, "手记添加成功但图片存取失败!"),
    FAIL_SAVEFILEA(true, 202, "修改个人信息成功,但头像未更新成功!"),
    SUCCESS_ADDARTICLE(true, 200, "添加手记成功!");
    private Boolean status; //响应是否成功
    private Integer code; //返回码
    private String message; //返回消息
    ResultCodeEnum(Boolean status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

}