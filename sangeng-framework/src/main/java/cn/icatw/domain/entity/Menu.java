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
 * 菜单权限表(Menu)实体类
 *
 * @author icatw
 * @since 2022-10-27 21:23:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
@ApiModel("Menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = -58694882366306597L;
    /**
     * 菜单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    @TableField(value = "menu_name")
    @ApiModelProperty("菜单名称")
    private String menuName;

    /**
     * 父菜单ID
     */
    @TableField(value = "parent_id")
    @ApiModelProperty("父菜单ID")
    private Long parentId;

    /**
     * 显示顺序
     */
    @TableField(value = "order_num")
    @ApiModelProperty("显示顺序")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @TableField(value = "path")
    @ApiModelProperty("路由地址")
    private String path;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    @ApiModelProperty("组件路径")
    private String component;

    /**
     * 是否为外链（0是 1否）
     */
    @TableField(value = "is_frame")
    @ApiModelProperty("是否为外链（0是 1否）")
    private Integer isFrame;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    @TableField(value = "menu_type")
    @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
    private String menuType;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    @TableField(value = "visible")
    @ApiModelProperty("菜单状态（0显示 1隐藏）")
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    @TableField(value = "status")
    @ApiModelProperty("菜单状态（0正常 1停用）")
    private String status;

    /**
     * 权限标识
     */
    @TableField(value = "perms")
    @ApiModelProperty("权限标识")
    private String perms;

    /**
     * 菜单图标
     */
    @TableField(value = "icon")
    @ApiModelProperty("菜单图标")
    private String icon;

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

    @TableField(value = "del_flag")
    @ApiModelProperty("$column.comment")
    private String delFlag;
}

