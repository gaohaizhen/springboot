package com.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyRabbitmq {
    private String url = "127.0.0.1";

    private String Port = "5679";
}
