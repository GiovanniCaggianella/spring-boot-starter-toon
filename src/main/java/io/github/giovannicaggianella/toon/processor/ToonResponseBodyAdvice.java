package io.github.giovannicaggianella.toon.processor;

import io.github.giovannicaggianella.toon.annotation.ToonResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Intercepts controller responses annotated with {@link ToonResponse} and encodes the
 * body into TOON format before it is written to the HTTP response.
 */
public class ToonResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final ToonAnnotationProcessor processor;

    public ToonResponseBodyAdvice(ToonAnnotationProcessor processor) {
        this.processor = processor;
    }

    @Override
    public boolean supports(MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        return resolveAnnotation(returnType) != null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        ToonResponse toonResponse = resolveAnnotation(returnType);
        if (toonResponse == null) {
            return body;
        }

        if (response != null) {
            response.getHeaders().setContentType(MediaType.parseMediaType(toonResponse.contentType()));
        }

        if (body == null) {
            return null;
        }

        return processor.encode(body, toonResponse.lengthMarker());
    }

    private ToonResponse resolveAnnotation(MethodParameter returnType) {
        ToonResponse annotation = null;
        if (returnType.getMethod() != null) {
            annotation = AnnotatedElementUtils.findMergedAnnotation(returnType.getMethod(), ToonResponse.class);
        }
        if (annotation == null) {
            annotation = AnnotatedElementUtils.findMergedAnnotation(returnType.getContainingClass(), ToonResponse.class);
        }
        return annotation;
    }
}
