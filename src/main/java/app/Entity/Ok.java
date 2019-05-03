package app.Entity;

import app.Enum.ResType;
import org.json.JSONObject;

import java.util.Map;

public class Ok {
    private ResType resType;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String msg;

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    private Map data;
    public String toString() {
        JSONObject res = new JSONObject(this);
        return res.toString();
    }

    public Ok (ResType resType) {
        this.resType = resType;
    }
    public int errCode;
    public int getErrCode() {
        switch (this.resType) {
            case Fail:
                errCode = 500;
                break;
            case Logout:
                errCode = 506;
                break;
            default:
                errCode = 200;
        }
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
