package com.MeddicheTruck.mtcore.aspects;

import com.MeddicheTruck.mtcore.annotations.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


// Source :https://howtodoinjava.com/spring-aop/aspectj-around-annotation-example/#:~:text=AspectJ%20%40Around%20Annotation%20Usage,do%20not%20invoke%20the%20ProceedingJoinPoint.
@Aspect
@Component
public class DtoFieldFilterAspect {

    // Get around the methods in BaseController that have in their @parameters a dto and are annotated with @FilterDtoFields
    @Around("execution(* com.MeddicheTruck.mtcore.base.BaseController.*(..)) && args(.., @com.MeddicheTruck.mtcore.annotations.FilterDtoFields dto)")
    public Object filterFields(ProceedingJoinPoint joinPoint, Object dto) throws Throwable {

        Method interceptedMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();

        if (dto != null && dto.getClass().isAnnotationPresent(AdaptedDto.class)) {
            Field[] fields = dto.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!shouldIncludeField(field, interceptedMethod)) {
                    field.setAccessible(true);
                    field.set(dto, null);
                    System.out.println("Field " + field.getName() + " was set to null");
                }
            }
        }

        return joinPoint.proceed();
    }

    private boolean shouldIncludeField(Field field, Method method) {
        if (field.isAnnotationPresent(IncludeOnPostRequest.class) && method.isAnnotationPresent(PostMapping.class)) {
            return true;
        }

        if (field.isAnnotationPresent(IncludeOnPutRequest.class) && method.isAnnotationPresent(PutMapping.class)) {
            return true;
        }

        return field.isAnnotationPresent(IncludeOnAllRequests.class);
    }
}
