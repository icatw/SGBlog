package cn.icatw.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色信息表(Role)实体类
 *
 * @author icatw
 * @since 2022-10-27 21:23:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role")
@ApiModel("Role")
public class Role implements Serializable {
    private static final long serialVersionUID = -41986565619023690L;
    /**
     * 角色ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    @ApiModelProperty("角色名称")
    private String roleName;

    /**
     * 角色权限字符串
     */
    @TableField(value = "role_key")
    @ApiModelProperty("角色权限字符串")
    private String roleKey;

    /**
     * 显示顺序
     */
    @TableField(value = "role_sort")
    @ApiModelProperty("显示顺序")
    private Integer roleSort;

    /**
     * 角色状态（0正常 1停用）
     */
    @TableField(value = "status")
    @ApiModelProperty("角色状态（0正常 1停用）")
    private String status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField(value = "del_flag")
    @ApiModelProperty("删除标志（0代表存在 1代表删除）")
    private String delFlag;

    /**
     * 创建者
     */
    @TableField(value = "create_by")
    @ApiModelProperty("创建者")
    private Long createBy;

    /**创建时间*/
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @TableField(value = "update_by")
    @ApiModelProperty("更新者")
    private Long updateBy;

    /**更新时间*/
    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @TableField(value = "remark")
    @ApiModelProperty("备注")
    private String remark;
}

