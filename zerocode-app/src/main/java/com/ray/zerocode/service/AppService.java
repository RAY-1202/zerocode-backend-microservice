package com.ray.zerocode.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.ray.zerocode.model.dto.app.AppAddRequest;
import com.ray.zerocode.model.dto.app.AppQueryRequest;
import com.ray.zerocode.model.entity.App;
import com.ray.zerocode.model.entity.User;
import com.ray.zerocode.model.vo.AppVO;
import reactor.core.publisher.Flux;

import java.io.Serializable;
import java.util.List;

/**
 * 应用 服务层。
 *
 * @author <a href="https://github.com/RAY-1202">Ray</a>
 */
public interface AppService extends IService<App> {

    /**
     * 获取脱敏后的应用信息
     * @param
     * @return
     */
    AppVO getAppVO(App app);

    /**
     * 获取查询条件
     * @param appQueryRequest 查询参数
     * @return 查询结果
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    /**
     * 获取脱敏后的应用列表
     * @param appList 应用列表
     * @return 脱敏后的应用列表
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 应用聊天生成代码
     * @param appId 应用 id
     * @param message 提示词
     * @param loginUser 登录用户
     * @return 生成的代码
     */
    Flux<String> chatToGenCode(Long appId, String message, User loginUser);

    /**
     * 应用部署
     * @param appId 应用 id
     * @param loginUser 登录用户
     * @return 部署结果
     */
    String deployApp(Long appId, User loginUser);

    /**
     * 删除应用时关联删除对话历史
     *
     * @param id 应用ID
     * @return 是否成功
     */
    boolean removeById(Serializable id);

    /**
     * 异步生成应用截图并更新封面
     *
     * @param appId  应用ID
     * @param appUrl 应用访问URL
     */
    void generateAppScreenshotAsync(Long appId, String appUrl);

    /**
     * 创建应用
     * @param appAddRequest
     * @param loginUser
     * @return
     */
    Long createApp(AppAddRequest appAddRequest, User loginUser);
}
