package com.snapp.price.snappprice.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class BaseResponseDto<T> implements Serializable {
    private String message;
    private T result;
}
