package org.lucanscr.cachedemo.utils;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        return String.format("%s_%s_%s",
                target.getClass().getSimpleName(),
                method.getName(),
                StringUtils.arrayToDelimitedString(params, "_"));
    }
}
