package com.demo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyRedis {

    private String url = "127.0.0.1";

    private String Port = "6379";

}
