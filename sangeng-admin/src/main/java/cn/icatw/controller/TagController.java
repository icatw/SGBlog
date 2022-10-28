package cn.icatw.controller;

import cn.icatw.domain.ResponseResult;
import cn.icatw.domain.dto.TagListDto;
import cn.icatw.domain.entity.Tag;
import cn.icatw.domain.vo.PageVo;
import cn.icatw.domain.vo.TagVo;
import cn.icatw.service.TagService;
import cn.icatw.utils.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("{id}")
    public ResponseResult getById(@PathVariable Long id) {
        Tag tag = tagService.getById(id);
        TagVo tagVo = BeanCopyUtils.copyBean(tag, TagVo.class);
        return ResponseResult.okResult(tagVo);
    }

    @PutMapping()
    public ResponseResult updateTag(@RequestBody Tag tag) {
        tagService.updateById(tag);
        return ResponseResult.okResult();
    }

    @GetMapping("/list")
    public ResponseResult<PageVo> list(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        PageVo result = tagService.pageList(pageNum, pageSize, tagListDto);
        return ResponseResult.okResult(result);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag() {
        List<Tag> list = tagService.list();
        List<TagVo> result = BeanCopyUtils.copyBeanList(list, TagVo.class);
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
