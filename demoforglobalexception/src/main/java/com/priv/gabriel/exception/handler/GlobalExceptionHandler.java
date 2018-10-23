package com.priv.gabriel.exception.handler;

import com.priv.gabriel.exception.TempException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-23
 * @Description:
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = TempException.class)
	public TempException tempErrHandler(TempException e){
		TempException exception = new TempException(e.getCode(),e.getMessage());
		return exception;
	}
}