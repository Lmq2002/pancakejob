package com.jbgz.pancakejob.utils;

import com.jbgz.pancakejob.common.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultData {
    public int code;
    //public boolean status;
    public String message;

    //public List<Object> data= new ArrayList<>();

    public Map<String, Object> data = new HashMap<>();

//    public ResultData()
//    {
//        code = 300;
//        //status = false;
//        message = "请求失败";
//    }

//    public String ReturnJson(){
//        String jsonStr = JSON.toJSONString(this);
//        return jsonStr;
//    }

    public static ResultData error(){
        return new ResultData();
    }
    public static ResultData error(Integer code, String message) {
        return new ResultData(code,message,null);
    }
    public static ResultData sys_error(){
        return new ResultData(Constants.CODE_401,"SYSTEM ERROR",null);
    }
    public static ResultData success(Map<String, Object> data){
        return new ResultData(Constants.CODE_200,"",data);
    }

}

