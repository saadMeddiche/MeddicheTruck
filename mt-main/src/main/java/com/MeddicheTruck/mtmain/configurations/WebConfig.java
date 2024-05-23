package com.MeddicheTruck.mtmain.configurations;

import com.MeddicheTruck.mtmain.properties.StaticContent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final StaticContent staticContent;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String absolutePath = staticContent.getUploadDirectoryAbsolutePath();
        String url = String.format("/%s/**", staticContent.getUrl());

        registry.addResourceHandler(url)
                .addResourceLocations(String.format("file:%s", absolutePath));
    }
}
