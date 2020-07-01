package com.zxyun.common.poi.w.model;

import com.zxyun.common.poi.w.eunm.XsgCellType;

/**
 * 单元格
 */
public class XsgCell {

    /**
     * 单元格类型
     */
    private XsgCellType cellType;

    /**
     * 单元格值
     */
    private Object value;

    /**
     * 单元格靠齐方式
     */
    private String align;

    /**
     * 单元格字体
     */
    private String font;

    /**
     * 颜色
     */
    private String color;

    /**
     * 
     */
    private Integer index;

    public XsgCellType getCellType() {
        return cellType;
    }

    public void setCellType(XsgCellType cellType) {
        this.cellType = cellType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getAlign() {
        return align;
    }

    public void setAlign(String align) {
        this.align = align;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
