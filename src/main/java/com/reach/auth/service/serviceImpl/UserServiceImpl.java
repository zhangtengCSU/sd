package com.reach.auth.service.serviceImpl;

import cn.hutool.core.util.RandomUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.reach.auth.domain.ClaimType;
import com.reach.auth.domain.po.UserPO;
import com.reach.auth.domain.vo.LoginVO;
import com.reach.auth.domain.vo.RegisterVO;
import com.reach.auth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reach.common.exception.ReachException;
import com.reach.common.response.ResponseEnum;
import com.reach.common.utils.IdUtil;
import com.reach.common.utils.JwtUtil;
import com.reach.common.utils.RedisUtil;
import com.reach.mappers.UserMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserPO> implements UserService {
    public static final String OAUTH_PREFIX = "oauth:";

    private static final String SECRET = "cFTkAoAXdPY!6#&@qHpxBXqs8g4mX&OB";

    @Override
    public String auth(String publicKey) {
        // find userId by pk
        String userId = findUserIdByPublicKey(publicKey);
        if (StringUtils.isBlank(userId)) {
            throw ReachException.build(ResponseEnum.USER_NOT_REGISTER);
        }
        String key = OAUTH_PREFIX + userId;
        // find if cache exist
        String ifExist = RedisUtil.getString(key);
        if (!StringUtils.isEmpty(ifExist)) {
            try {
                JWT.require(Algorithm.HMAC256(SECRET)).build().verify(ifExist);
            } catch (TokenExpiredException e) {
                String token = JwtUtil.createToken(ClaimType.USER_ID, userId);
                // save token to cache
                RedisUtil.deleteKey(key);
                RedisUtil.setString(key, token, 60 * 60);
                return token;
            }
            return ifExist;
        }
        String token = JwtUtil.createToken(ClaimType.USER_ID, userId);
        // save token to cache
        RedisUtil.setString(key, token, 60 * 60);
        return token;
    }

    @Override
    public RegisterVO register(String publicKey, String userName) {
        // check public key
        String exist = findUserIdByPublicKey(publicKey);
        if (StringUtils.isNotEmpty(exist)) {
            throw ReachException.build(ResponseEnum.USER_HAS_BEEN_REGISTERED);
        }
        // generate username suffix
        String username = generateUsernameSuffix(userName);
        // save user info
        UserPO userPO = addUser(publicKey, userName);
        return RegisterVO
                .builder()
                .user_id(userPO.getUserId())
                .user_name(username)
                .user_public_key(userPO.getPublicKey())
                .logo(userPO.getLogo())
                .token(auth(userPO.getPublicKey()))
                .build();
    }

    @Override
    public String findUserNameById(String userId) {
        return this.getBaseMapper().selectOne(new QueryWrapper<UserPO>().lambda().eq(UserPO::getUserId, userId)).getUserName();
    }

    @Override
    public UserPO addUser(String publicKey, String userName) {
        String id = IdUtil.getId();
        UserPO build = UserPO
                .builder()
                .userId(id)
                .userName(userName)
                .publicKey(publicKey)
                .logo("logo")
                .build();
        this.save(build);
        return build;
    }

    @Override
    public String findUserIdByPublicKey(String publicKey) {
        UserPO userByPublicKey = findUserByPublicKey(publicKey);
        if (Objects.isNull(userByPublicKey)) {
            return null;
        }
        return userByPublicKey.getUserId();
    }

    @Override
    public UserPO findUserByPublicKey(String publicKey) {
        UserPO userPO = this.baseMapper.selectOne(
                new QueryWrapper<UserPO>()
                        .lambda()
                        .eq(UserPO::getPublicKey, publicKey)
        );
        if (Objects.isNull(userPO)) {
            return null;
        }
        return userPO;
    }

    @Override
    public UserPO findUserByUserName(String userName) {
        UserPO userPO = this.baseMapper.selectOne(
                new QueryWrapper<UserPO>()
                        .lambda()
                        .eq(UserPO::getUserName, userName)
        );
        if (Objects.isNull(userPO)) {
            return null;
        }
        return userPO;
    }

    private boolean ifUserNameExist(String userName) {
        UserPO userByUserName = findUserByUserName(userName);
        if (Objects.isNull(userByUserName)) {
            return false;
        }
        return !userByUserName.getUserName().equals(userName);
    }

    private String generateUsernameSuffix(String username) {
        String generate = StringUtils.EMPTY;
        for (int i = 0; i < 20; i++) {
            // random string
            String suffix = RandomUtil.randomString(4);
            // assemble
            generate = username + "#" + suffix;
            // check whether used
            if (!ifUserNameExist(username)) {
                break;
            }
        }
        return generate;
    }

    @Override
    public void ifAuth(String token) {
        try {
            JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
        } catch (TokenExpiredException e) {
            throw ReachException.build(ResponseEnum.TOKEN_EXPIRED);
        } catch (JWTVerificationException e) {
            throw ReachException.build(ResponseEnum.TOKEN_ERROR);
        } catch (Exception e) {
            throw ReachException.build(ResponseEnum.TOKEN_ERROR);
        }
    }
}
