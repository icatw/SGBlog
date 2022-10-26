package cn.icatw.service;

import cn.icatw.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author icatw
 * @date 2022/10/26
 * @email 762188827@qq.com
 * @apiNote
 */
public interface UploadService {
    /**
     * 上传img
     *
     * @param img img
     * @return {@link ResponseResult}
     */
    ResponseResult uploadImg(MultipartFile img);
}
