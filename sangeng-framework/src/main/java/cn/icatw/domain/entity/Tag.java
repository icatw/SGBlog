package cn.icatw.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签(Tag)实体类
 *
 * @author icatw
 * @since 2022-10-27 16:51:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("Tag")
@TableName("sg_tag")
public class Tag implements Serializable {
    private static final long serialVersionUID = 719913724120536978L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 标签名
     */
    @TableField(value = "name")
    @ApiModelProperty("标签名")
    private String name;

    @TableField(value = "create_by")
    @ApiModelProperty("$column.comment")
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty("$column.comment")
    private Date createTime;

    @TableField(value = "update_by")
    @ApiModelProperty("$column.comment")
    private Long updateBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("$column.comment")
    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @TableField(value = "del_flag")
    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty("备注")
    private String remark;
}

