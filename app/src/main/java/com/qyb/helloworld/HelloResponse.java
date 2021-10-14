package com.qyb.helloworld;

import java.util.List;

public class HelloResponse {
        private String status;
        private String msg;
        private TestChat data;
        private String ok;

        public String getMsg() { return msg; }
        public String getStatus() { return status; }
        public String getOk() { return ok; }

    public TestChat getData() {
        return data;
    }

    public void setData(TestChat data) {
        this.data = data;
    }

    public void setOk(String ok) { this.ok = ok; }
        public void setMsg(String msg) { this.msg = msg; }
        public void setStatus(String status) { this.status = status; }
}
