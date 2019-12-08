package com.haojing.exception;

import com.haojing.utils.JSONresult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public JSONresult handleMaxUploadFile(MaxUploadSizeExceededException ex){
        return JSONresult.errorMsg("文件上传超过500K");
    }
}
