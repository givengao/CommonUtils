package model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @Comment: null
 */
@TableName("jbd_product_reason")
public class ProductReason {
    /** 唯一id */
    @TableId("id")
    private Long id;

    /** 关联菜品库主键id */
    @TableField("productInfoId")
    private Long productInfoId;

    /** 验证状态 (0-验证失败原因、1-无法采购原因、2-启用原因、3-禁用原因) */
    @TableField("reasonType")
    private Integer reasonType;

    /** 原因描述 */
    @TableField("reason")
    private String reason;

    /** 操作人 */
    @TableField("operate")
    private String operate;

    /** 创建时间 */
    @TableField("createDate")
    private Date createDate;

    /** 更新时间 */
    @TableField("updateDate")
    private Date updateDate;

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

    public Integer getReasonType() {
        return reasonType;
    }

    public void setReasonType(Integer reasonType) {
        this.reasonType = reasonType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}