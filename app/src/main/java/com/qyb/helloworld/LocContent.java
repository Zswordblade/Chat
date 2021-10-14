package com.qyb.helloworld;

public class LocContent {
    private static final long serialVersionUID = 8021381444738260454L;

    private Integer action;		// 动作类型
    private LocMsg locMsg;	// 用户的位置
    private String extend;		// 扩展字段

    public Integer getAction() {
        return action;
    }
    public void setAction(Integer action) {
        this.action = action;
    }

    public LocMsg getLocMsg() {
        return locMsg;
    }

    public void setLocMsg(LocMsg locMsg) {
        this.locMsg = locMsg;
    }

    public String getExtend() {
        return extend;
    }
    public void setExtend(String extend) {
        this.extend = extend;
    }
}
