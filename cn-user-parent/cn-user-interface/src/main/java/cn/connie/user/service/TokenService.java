package cn.connie.user.service;

import cn.connie.common.exception.CustomException;
import cn.connie.user.to.TokenTO;

public interface TokenService {
    /**
     * 创建TOKEN
     *
     * @param userId 用户ID
     * @return
     */
    TokenTO createToken(String userId);

    /**
     * 根据登录令牌获取TOKEN
     *
     * @param accessToken 登录令牌
     * @return
     */
    TokenTO getToken(String accessToken);

    /**
     * 根据登录令牌删除
     *
     * @param accessToken 登录令牌
     */
    void deleteToken(String accessToken);

    /**
     * 刷新新令牌
     *
     * @param refreshToken 刷新令牌
     * @return
     * @throws CustomException
     */
    TokenTO refreshToken(String refreshToken);
}
