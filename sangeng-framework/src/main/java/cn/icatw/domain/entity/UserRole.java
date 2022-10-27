package cn.icatw.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户和角色关联表(UserRole)实体类
 *
 * @author icatw
 * @since 2022-10-27 21:23:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role")
@ApiModel("UserRole")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 568520144685239134L;
    /**
     * 用户ID
     */
    //@TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;
    /**
     * 角色ID
     */
    //@TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;
}

