package br.dorotech.trace;

import io.jaegertracing.internal.JaegerSpanContext;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
public class TracerHandler implements ContainerResponseFilter {

    @Inject
    Tracer tracer;

    private static final String TRACE_ID = "traceId";

    @Override
    public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext)
            throws IOException {
        final SpanContext spanContext = tracer.scopeManager().activeSpan().context();
        if (spanContext instanceof JaegerSpanContext) {
            responseContext.getHeaders().add(TRACE_ID, ((JaegerSpanContext) spanContext).getTraceId());
        }
    }

}
