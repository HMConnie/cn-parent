package cn.connie.common.exception;

public class CustomException extends Exception {

    private String tips;

    public CustomException(String msg, String tips) {
        super(msg);
        this.tips = tips;
    }

    public String getTips() {
        return tips;
    }
}
