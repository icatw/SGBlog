package cn.icatw.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author icatw
 * @date 2022/10/29
 * @email 762188827@qq.com
 * @apiNote
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenusCheckedKeysVo {
    private List<MenuTreeVo> menus;
    private List<Long> checkedKeys;
}
