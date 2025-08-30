package com.ray.zerocode.service;

/**
 * 截图服务
 */
public interface ScreenshotService {

    /**
     * 截图并上传
     * @param webUrl 网页地址
     * @return 访问URL
     */
    String generateAndUploadScreenshot(String webUrl);

}
