package com.MeddicheTruck.mtcore.aspects;

import com.MeddicheTruck.mtcore.annotations.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class DtoFieldFilterAspect {

    @Around("@annotation(com.MeddicheTruck.mtcore.annotations.FilterDtoFields) && args(dto)")
    public Object filterFields(ProceedingJoinPoint joinPoint, Object dto) throws Throwable {
        System.out.println("Filtering fields");
        if (dto != null && dto.getClass().isAnnotationPresent(AdaptedDto.class)) {
            Field[] fields = dto.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!shouldIncludeField(field, HttpMethod.valueOf(joinPoint.getArgs()[0].toString()))) {
                    field.setAccessible(true);
                    field.set(dto, null);
                }
            }
        }
        return joinPoint.proceed();
    }

    private boolean shouldIncludeField(Field field, HttpMethod method) {
        if (field.isAnnotationPresent(IncludeOnPostRequest.class) && method == HttpMethod.POST) {
            return true;
        }

        if (field.isAnnotationPresent(IncludeOnPutRequest.class) && method == HttpMethod.PUT) {
            return true;
        }

        return field.isAnnotationPresent(IncludeOnAllRequests.class);
    }
}
