package com.zkf.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author wangcj
 * @version 1.0.0
 * @CreateTime 2016/10/28 14:19
 * @Description
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class VResult<T> {

	private Integer code;

	private boolean success;

	private String message;

	private String type;

	private T content;

	private List<T> contents;

	public VResult() {
		super();
	}

	public VResult(boolean success) {
		this.success = success;
	}

	public VResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public VResult(boolean success, T content) {
		this.success = success;
		this.content = content;
	}

	public VResult(T content, String type) {
		this.content = content;
		this.type = type;
	}

	public VResult(boolean success, T content, String type) {
		this.success = success;
		this.content = content;
		this.type = type;
	}

	public VResult(boolean success, List<T> contents) {
		this.success = success;
		this.contents = contents;
	}

	public VResult(boolean success, List<T> contents, String type) {
		this.success = success;
		this.contents = contents;
		this.type = type;
	}
	public VResult(boolean success, List<T> contents, String type, String message) {
	    this.success = success;
	    this.contents = contents;
	    this.type = type;
	    this.message = message;
	}

	public static <T> VResult<T> of(boolean success, List<T> contents) {
		return  new VResult(success, contents);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public List<T> getContents() {
		return contents;
	}

	public void setContents(List<T> contents) {
		this.contents = contents;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
