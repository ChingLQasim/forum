package com.soft.forum.Utils;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
public class Res {
    /*
    操作是否成功
     */
    private Boolean success;
    /*
    返回码
     */
    private Integer code;
    /*
    返回的消息
     */
    private String message;
    /*
    返回数据
     */
    private Map<String,Object> data = new HashMap<>();
    public Res(){};
    /*
    操作成功响应，无参数返回通用成功码
     */
    public static Res ok(){
        Res r = new Res();
        r.setCode(resCodeEnum.SUCCESS.getCode());
        r.setSuccess(resCodeEnum.SUCCESS.getStatus());
        r.setMessage(resCodeEnum.SUCCESS.getMessage());
        return r;
    }
    /*
    操作失败响应，无参数，返回未知原因错误
     */
    public static Res error(){
        Res r = new Res();
        r.setCode(resCodeEnum.UNKNOW_REASON.getCode());
        r.setSuccess(resCodeEnum.UNKNOW_REASON.getStatus());
        r.setMessage(resCodeEnum.UNKNOW_REASON.getMessage());
        return r;
    }
    /*
    操作成功响应，有参数，返回对应的操作
     */
    public static Res ok(resCodeEnum codeEnum){
        Res r = new Res();
        r.setCode(codeEnum.getCode());
        r.setSuccess(codeEnum.getStatus());
        r.setMessage(codeEnum.getMessage());
        return r;
    }
    /*
    操作失败响应，有参数，返回对应原因
     */
    public static Res error(resCodeEnum codeEnum){
        Res r = new Res();
        r.setCode(codeEnum.getCode());
        r.setSuccess(codeEnum.getStatus());
        r.setMessage(codeEnum.getMessage());
        return r;
    }
    public Res data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
    public <T> Res data(List<T> map){
        Map<String, Object> tempMap = new HashMap<>();
        int i = 0;
        for (Object obj: map) {
            tempMap.put(String.valueOf(i++), obj);
        }
        this.setData(tempMap);
        return this;
    }

    public Res data(String key, Object value){
        this.data.put(key,value);
        return this;
    }

    public Res message(String message){
        this.setMessage(message);
        return this;
    }

    public Res code(Integer code){
        this.setCode(code);
        return this;
    }

    public Res success(Boolean success){
        this.setSuccess(success);
        return this;
    }
}
