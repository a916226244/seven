package com.seven.wx.provider.vo;

/**
 * Created by xiaozhiping on 18/3/18.
 */
public class UploadVo {

    public static int TOKEN_ERROR = 404;
    public static int OTHER_ERROR = 500;


    private int code = 200;

    private String message = "上传成功";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
