package com.lina.market_project.pojo;

public class Result<T> {//返回给前台的一个包装类
    private String code;
    private String msg;
    private T data;

    public Result() {
    }

    public Result(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static Result success(){
        Result result=new Result();

        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result=new Result<>(data);
        result.setCode("0");
        result.setMsg("成功");
        return result;
    }

    public static  Result error(){
        Result result=new Result();
        result.setCode("1");
        result.setMsg("失败");
        return result;
    }
    public static  Result error(String code,String msg){
        Result result=new Result();
        result.setCode("1");
        result.setMsg("失败");
        return result;
    }
}

