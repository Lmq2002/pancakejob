package com.jbgz.pancakejob.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.jbgz.pancakejob.entity.User;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class CreateJWT {
    private  static final long EXPIRE_DATE=30*60*100000;
    private static final String TOKEN_SECRET = "ZCEQIUBFKSJBFJH2020BQWE";

    public static String getToken(User user){
        String token ="";
        try{
            //过期时间
            Date date = new Date(System.currentTimeMillis()+EXPIRE_DATE);
            //密钥及加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String,Object> header = new HashMap<>();
            header.put("typ","JWT");
            header.put("alg","HS256");
            //携带userid,password信息，生成签名
            token = JWT.create()
                    .withHeader(header)
                    .withClaim("userId",user.getUserId()+"")
                    .withClaim("password",user.getPassword()).withExpiresAt(date)
                    .sign(algorithm);
        }
        catch (Exception e)
        {
            System.out.println("token生成异常："+e.getMessage());
            return null;
        }
        return token;
    }

    public static boolean verifyToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }
        catch (Exception e){
            System.out.println("token验证异常："+e.getMessage());
            return false;
        }
    }
}
