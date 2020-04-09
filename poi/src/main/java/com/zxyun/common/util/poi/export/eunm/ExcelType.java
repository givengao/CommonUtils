package com.zxyun.common.util.poi.export.eunm;

/**
 * @des:
 * @Author: given
 * @Date 2020/3/19 18:17
 */
public enum  ExcelType {
    NONE,
    XLS(".xls"),
    XLSX(".xlsx");

    private String suffix;

    private ExcelType() {
    }

    private ExcelType(String suffix) {
        this.suffix = suffix;
    }

    /**
     * 获取类型后缀名
     */
    public String getSuffix() {
        return suffix;
    }
}
