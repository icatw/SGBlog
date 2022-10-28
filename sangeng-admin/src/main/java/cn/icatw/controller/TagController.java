package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.dto.TagListDto;
import cn.icatw.domain.entity.Tag;
import cn.icatw.domain.vo.PageVo;
import cn.icatw.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author icatw
 * @date 2022/10/27
 * @email 762188827@qq.com
 * @apiNote
 */
@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        PageVo result = tagService.pageList(pageNum, pageSize, tagListDto);
        return ResponseResult.okResult(result);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody Tag tag) {
        tagService.save(tag);
        return ResponseResult.okResult();
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteTag(@PathVariable Long id) {
        tagService.removeById(id);
        return ResponseResult.okResult();
    }
}
