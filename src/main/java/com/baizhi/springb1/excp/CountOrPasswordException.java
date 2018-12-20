package com.baizhi.springb1.excp;

public class CountOrPasswordException extends AdminException {

    private String errCode;
    private String errMsg;



    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public CountOrPasswordException(){}
    public CountOrPasswordException(String errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
