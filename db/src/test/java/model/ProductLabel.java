package model;


import com.zxyun.common.db.mysql.annotation.TableField;
import com.zxyun.common.db.mysql.annotation.TableId;
import com.zxyun.common.db.mysql.annotation.TableName;

/**
 * @Comment: null
 */
@TableName("jbd_product_label")
public class ProductLabel {
    /** 唯一id */
    @TableId("id")
    private Long id;

    /** 商品采集库id */
    @TableField("productInfoId")
    private Long productInfoId;

    /** 类型 */
    @TableField("type")
    private Integer type;

    /** 属性名 */
    @TableField("field")
    private String field;

    /** 属性值 */
    @TableField("value")
    private String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(Long productInfoId) {
        this.productInfoId = productInfoId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}