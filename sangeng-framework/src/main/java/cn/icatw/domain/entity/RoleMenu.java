package cn.icatw.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 角色和菜单关联表(RoleMenu)实体类
 *
 * @author icatw
 * @since 2022-10-27 21:23:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_menu")
@ApiModel("RoleMenu")
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = -90411853650936255L;
    /**
     * 角色ID
     */
    //@TableId(value = "role_id", type = IdType.AUTO)
    //@MppMultiId
    private Long roleId;
    /**
     * 菜单ID
     */
    //@TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;
}

