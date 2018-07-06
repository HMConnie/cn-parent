package cn.connie.user.core.impl;

import cn.connie.user.service.TokenService;
import cn.connie.user.to.TokenTO;
import com.sgcai.commons.lang.utils.Dui1DuiStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public TokenTO createToken(String userId) {
        TokenTO tokenTO = tokenRepository.getToken(userId);
        if (tokenTO != null && !StringUtils.isBlank(tokenTO.getAccessToken())) {
            tokenRepository.delete(tokenTO.getAccessToken());
        }
        tokenTO = new TokenTO();
        tokenTO.setAccessToken(Dui1DuiStringUtils.generateUUID());
        tokenTO.setExpiredIn(TokenRepository.ACCESS_TOKEN_EXPIRED_SECOND);
        tokenTO.setRefreshToken(Dui1DuiStringUtils.generateUUID());
        tokenTO.setUserId(userId);
        tokenRepository.save(tokenTO);
        return tokenTO;
    }

    @Override
    public TokenTO getToken(String accessToken) {
        return tokenRepository.getTokenByAccessToken(accessToken);
    }

    @Override
    public void deleteToken(String accessToken) {
        tokenRepository.delete(accessToken);
    }

    @Override
    public TokenTO refreshToken(String refreshToken) {
        return tokenRepository.getTokenByRefreshToken(refreshToken);
    }
}
