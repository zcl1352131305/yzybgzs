package cn.gov.spb.cq.yzglfzgj.enums;

/**
 * Created with IntelliJ IDEA.
 * User: lhl
 * Date: 2017/09/19
 * Time: 11:26
 */
public enum ResultEnum {
    SUCCESS(100, "操作成功!"),
    ERROR(300,"系统异常!"),
    NOT_FOUND(301, "信息不存在");
    private Integer code;
    private String msg;
    ResultEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
