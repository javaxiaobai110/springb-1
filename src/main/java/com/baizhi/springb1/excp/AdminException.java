package com.baizhi.springb1.excp;

public class AdminException extends RuntimeException {

    /*private String errCode;
    private String errMsg;

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }*/

    public AdminException() {
    }

   /* public AdminException(String errCode,String errMsg) {
        this.errCode = errCode;this.errMsg=errMsg;
    }*/

   public AdminException(String msg){super(msg);}

}
