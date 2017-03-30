package com.iseeyou.kang.netframework.api;

/**
 * Created by kGod on 2017/2/28.
 * Email 18252032703@163.com
 * Thank you for watching my code
 * 自定义异常
 */

public class ApiException extends Exception {

    public ApiException(String errorMsg)
    {
        super(errorMsg);
    }
}
