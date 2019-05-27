package file.service.web.handler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerParameter {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final ServletContext servletContext;

    /**
     * @param request
     * @param response
     */
    public HandlerParameter( HttpServletRequest request, HttpServletResponse response, ServletContext servletContext ) {
        this.request = request;
        this.response = response;
        this.servletContext = servletContext;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }
}
