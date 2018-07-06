package cn.connie.common.type;

public enum CashierBusinessType {
    BENBEN_TRANSACTION(1),//交易
    BENBEN_WITHDRAW(2),//提现
    BENBEN_RECHARGE(3),//充值
    BENBEN_BUY(4),
    BENBEN_REFUND(5),
    STOREHOUSE_CLEAN_FEE(6);//仓库清货费用

    private int id;

    private CashierBusinessType(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public static CashierBusinessType getInstance(int id) {
        for (CashierBusinessType status: CashierBusinessType.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("cant instance CashierBusinessType for ID:" + id);
    }

}
