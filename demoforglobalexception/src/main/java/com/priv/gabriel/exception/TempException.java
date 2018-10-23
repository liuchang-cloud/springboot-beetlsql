package com.priv.gabriel.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with Intellij IDEA.
 *
 * @Author: Gabriel
 * @Date: 2018-10-23
 * @Description:
 */
@Data
@AllArgsConstructor
public class TempException extends RuntimeException {

	private String code;

	private String message;
}