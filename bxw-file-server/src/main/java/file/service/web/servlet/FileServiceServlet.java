package file.service.web.servlet;

import file.service.web.handler.HandlerDispatcherFactory;
import file.service.web.handler.HandlerParameter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wen578351314@gmail.com
 * @version 0.0.1-SNAPSHOT
 * @Title: FileServiceDispatcher
 * @Description: 文件处理Servlet
 * @date 2015年12月8日
 */
@WebServlet(urlPatterns = "/fileServiceServlet", description = "文件服务处理servlet")
public class FileServiceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Log logger = LogFactory.getLog(getClass());
    private ServletContext servletContext;

    /**
     * Default constructor.
     */
    public FileServiceServlet() {

    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("----------------开始处理文件-----------------");
        HandlerDispatcherFactory handlerDispatcherFactory = HandlerDispatcherFactory.getInstance();
        handlerDispatcherFactory.createHandler().handle(new HandlerParameter(request, response, servletContext));
    }
}