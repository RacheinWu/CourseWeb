package com.he.ssm.config;

import cn.hutool.core.util.ReflectUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Spring MVC 配置
 *
 * @author tousen
 */
@Slf4j
@Configuration
public class Config implements WebMvcConfigurer {
    private static String[] pattern = new String[]{"yyyy-MM-dd'T'HH:mm:ss.SSSXXX", "HH:mm:ss", "yyyy-MM-dd", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S", "yyyy.MM.dd", "yyyy.MM.dd HH:mm", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm:ss.S", "yyyy/MM/dd", "yyyy/MM/dd HH:mm", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm:ss.S"};

    @Resource
    private  UploadConfig uploadConfig;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .allowCredentials(true)
                .allowedHeaders("*")
                .maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(uploadConfig.getDirResourcePath()+"/**")
                .addResourceLocations("file:"+uploadConfig.getDir()+"/");
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 请求常用的三种配置，*代表允许所有，也可以自定义属性（比如 header 只能带什么，只能是 post 方式等）
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }

    @Primary
    @Bean
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        log.info("自定义 ObjectMapper {}", "jacksonObjectMapper");
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleModule simpleModule = new SimpleModule();
        //@RequestBody 日期的全局转换
        simpleModule.addDeserializer(Date.class, dateDeserializer());
        objectMapper.registerModule(simpleModule);
        //json序列化Null值为""
        objectMapper.getSerializerProvider().setNullValueSerializer(nullValeSerializer());
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return objectMapper;
    }

    @Bean
    public DateConverter dateConverter() {
        return new DateConverter();
    }

    @Bean
    public DateDeserializer dateDeserializer() {
        return new DateDeserializer();
    }

    @Bean
    public NullValeSerializer nullValeSerializer() {
        return new NullValeSerializer();
    }

    static class DateConverter implements Converter<String, Date> {
        @Override
        public Date convert(String source) {
            log.info("{}", "DateConverter_convert");
            if (StringUtils.isBlank(source)) {
                return null;
            }
            try {
                long longDate = Long.parseLong(source.trim());
                return new Date(longDate);
            } catch (NumberFormatException e) {
                try {
                    return DateUtils.parseDate(source, pattern);
                } catch (ParseException pe) {
                    throw new RuntimeException(String.format("'%s' can not convert to type 'java.util.Date',just support timestamp(type of long) and following date format(%s)", source, StringUtils.join(pattern, ",")));
                }
            }
        }
    }

    static class DateDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
            log.info("{}", "DateDeserializer_deserialize");
            String originDate = p.getText();
            if (StringUtils.isBlank(originDate)) {
                return null;
            }
            try {
                //默认日期是毫秒值
                long longDate = Long.parseLong(originDate.trim());
                return new Date(longDate);
            } catch (NumberFormatException e) {
                try {
                    //日期为字符串值
                    return DateUtils.parseDate(originDate, pattern);
                } catch (ParseException pe) {
                    throw new IOException(String.format("'%s' can not convert to type 'java.util.Date',just support timestamp(type of long) and following date format(%s)", originDate, StringUtils.join(pattern, ",")));
                }
            }
        }

        @Override
        public Class<?> handledType() {
            return Date.class;
        }
    }

    static class NullValeSerializer extends JsonSerializer<Object> {
        @Override
        public void serialize(Object o, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
            Class<?> aClass = gen.getCurrentValue().getClass();
            String fieldName = gen.getOutputContext().getCurrentName();
            Field field = ReflectUtil.getField(aClass, fieldName);
            if (field != null) {
                if (Objects.equals(field.getType(), String.class) || Objects.equals(field.getType(), Date.class)) {
                    gen.writeString("");
                    return;
                } else if (Objects.equals(field.getType(), List.class) || Objects.equals(field.getType(), Set.class)) {
                    gen.writeStartArray();
                    gen.writeEndArray();
                    return;
                } else if (Objects.equals(field.getType(), Map.class) || Objects.equals(field.getType(), Object.class)) {
                    gen.writeStartObject();
                    gen.writeEndObject();
                    return;
                } else if (Objects.equals(field.getType(), Boolean.class)) {
                    gen.writeBoolean(false);
                    return;
                } else if (Objects.equals(field.getType(), Integer.class) || Objects.equals(field.getType(), Long.class) || Objects.equals(field.getType(), Double.class)) {
                    gen.writeNumber(0);
                    return;
                }
            }
            gen.writeString("");
        }
    }
}
