package com.zxyun.common.poi.w.eunm;

/**
 * 单元格类型
 */
public enum XsgCellType {

    /**
     * 未知
     */
    _NONE(-1),

    /**
     * 数字
     */
    NUMERIC(0),

    /**
     * 字符串
     */
    STRING(1),

    FORMULA(2),
    BLANK(3),
    BOOLEAN(4),
    ERROR(5);

    private final int code;

    private XsgCellType(int code) {
        this.code = code;
    }

    public static XsgCellType forInt(int code) {
        XsgCellType[] types = values();
        int len$ = types.length;

        for(int i = 0; i < len$; ++i) {
            XsgCellType type = types[i];
            if (type.code == code) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid CellType code: " + code);
    }

    public int getCode() {
        return this.code;
    }
}
