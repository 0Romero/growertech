package com.example.growertech.config;

import org.springframework.hateoas.Link;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class HateoasUtils {

    private static final Map<Method, Link> methodLinkMap = new HashMap<>();

    public static void registerLinkForMethod(Method method, Link link) {
        methodLinkMap.put(method, link);
    }

    public static Link getLinkForMethod(Method method) {
        return methodLinkMap.get(method);
    }

    public static Link createLink(UriComponentsBuilder uriBuilder, String rel) {
        return Link.of(uriBuilder.toUriString(), rel);
    }
}
