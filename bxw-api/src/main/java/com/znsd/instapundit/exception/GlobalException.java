package com.znsd.instapundit.exception;

import com.znsd.instapundit.result.CodeMsg;

import java.io.Serializable;

public class GlobalException extends RuntimeException implements Serializable {

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
