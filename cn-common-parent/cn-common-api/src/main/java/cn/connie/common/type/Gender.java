package cn.connie.common.type;

/**
 * 性别
 */
public enum Gender {
    MAN(0), WOMAN(1);

    private int id;

    Gender(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Gender getInstance(int id) {
        for (Gender status : Gender.values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        throw new IllegalArgumentException("cant instance Gender for ID:" + id);
    }
}
