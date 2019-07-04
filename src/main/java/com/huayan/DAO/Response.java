package com.huayan.DAO;

import java.io.Serializable;

public class Response implements Serializable { /**
	 * 
	 */
	private static final long serialVersionUID = 6635010497270369038L;
// 标记Serializable接口
	private String id;
    private String name;
    private String responseMessage;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getResponseMessage() {
        return responseMessage;
    }
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
