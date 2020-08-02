package com.dev.springdemo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by sunitc on 4/20/18.
 */
@Data
@AllArgsConstructor
public class ErrorMessage {
    private int statusCode;
    private String errorMessage;
}


