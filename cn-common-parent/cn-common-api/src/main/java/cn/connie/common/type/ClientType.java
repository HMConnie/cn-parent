package cn.connie.common.type;

public enum ClientType {

    CUSTOMER_ANDROID(0),    //客户安卓端
    CUSTOMER_IOS(1),        //客户IOS端
    MASTER_PC(2),           //团长PC端
    CUSTOMER_H5(3),         //客户H5端
    MASTER_H5(4);           //团长H5端


    private int id;

    ClientType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ClientType getInstance(int id) {
        for (ClientType type : ClientType.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("cant instance ClientType for ID:" + id);
    }
}
