package com.sist.ex0918_token.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultData<T> {
    private int totalCount; // length
    private String msg; // 메세지
    private T data;

    public static <T> ResultData<T> of(int totalCount, String msg, T data){
        return new ResultData<>(totalCount, msg, data);
    }

    public static <T> ResultData<T> of(int totalCount, String msg){
        return of(totalCount, msg, null);
    }

    public static <T> ResultData<T> of(String msg, T data){
        return of(0, msg, data);
    }
}
