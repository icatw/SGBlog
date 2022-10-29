package cn.icatw.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @author icatw
 * @date 2022/10/29
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
public class AddRoleDto {
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private List<Integer> menuIds;
    private String remark;
}
