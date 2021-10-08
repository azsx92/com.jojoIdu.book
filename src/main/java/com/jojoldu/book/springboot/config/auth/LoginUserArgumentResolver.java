package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver  implements HandlerMethodArgumentResolver {


    private final HttpSession httpSession;


    @Override
    public boolean supportsParameter(MethodParameter parameter) { //1
        boolean isLoginUserAnnotation   = parameter.getParameterAnnotation(LoginUser.class) != null;
        boolean isLoginUserClass        = SessionUser.class.equals(parameter.getParameterType());
        System.out.println("LoginUserArgumentResolver isLoginUserAnnotation++ [{}]" + isLoginUserAnnotation);
        System.out.println("LoginUserArgumentResolver isLoginUserClass++      [{}]" + isLoginUserClass);
        return isLoginUserAnnotation && isLoginUserClass;
    }

    @Override //2
    public Object resolveArgument(MethodParameter parameter
                                    , ModelAndViewContainer mavContainer
                                    , NativeWebRequest webRequest
                                    , WebDataBinderFactory binderFactory) throws Exception {
        System.out.println("1parameter++ [{}]" + parameter);
        System.out.println("1mavContainer++      [{}]" + mavContainer);
        System.out.println("1webRequest++      [{}]" + webRequest);
        System.out.println("1binderFactory++      [{}]" + binderFactory);
        System.out.println("1httpSession.getAttribute++      [{}]" + httpSession.getAttribute("user"));
        return httpSession.getAttribute("user");
    }
}
