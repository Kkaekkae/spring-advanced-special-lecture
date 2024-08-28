package com.sparta.spring.config.auth.additional;

import com.sparta.spring.application.auth.AuthUser;
import com.sparta.spring.config.auth.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.server.ResponseStatusException;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private final JwtUtil jwtUtil;

    public AuthUserHandlerMethodArgumentResolver(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        UseAuthUser annotation = parameter.getParameterAnnotation(UseAuthUser.class);
        if (annotation == null) return false;
        return parameter.getParameterType().equals(AuthUser.class);
    }

    @Override
    public AuthUser resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String token = jwtUtil.getJwtFromHeader(webRequest);
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                Claims info = jwtUtil.getUserInfoFromToken(token);
                return new AuthUser(info.getSubject());
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
}
