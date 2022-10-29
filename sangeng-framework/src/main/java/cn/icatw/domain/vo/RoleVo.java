package cn.icatw.domain.vo;

import lombok.Data;

/**
 * @author icatw
 * @date 2022/10/29
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
public class RoleVo {
    private Long id;
    private String roleKey;
    private String roleName;
    private Integer roleSort;
    private String status;
}
