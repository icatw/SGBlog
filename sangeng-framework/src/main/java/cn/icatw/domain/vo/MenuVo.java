package cn.icatw.domain.vo;

import lombok.Data;

/**
 * @author icatw
 * @date 2022/10/28
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
public class MenuVo {
    private String icon;
    private Long id;
    private String menuName;
    private String menuType;
    private String orderNum;
    private Long parentId;
    private String path;
    private String remark;
    private String status;
    private String visible;
}
