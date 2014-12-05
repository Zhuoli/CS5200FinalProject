package bbq.db.project.dao.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: maohao
 * Date: 14-12-4
 * Time: 下午11:05
 * To change this template use File | Settings | File Templates.
 */
public class StrutsUtil {

    private static final String HEADER_ENCODING = "encoding";
    private static final String HEADER_NOCACHE = "no-cache";
    private static final String DEFAULT_ENCODING = "UTF-8";
    private static final boolean DEFAULT_NOCACHE = true;

    public static void renderJson(final String jsonString, final String... headers) {
        render("application/json", jsonString, headers);
    }

    public static void render(final String contentType, final String content, final String... headers) {
        HttpServletResponse response = initResponseHeader(contentType, headers);
        try {
            response.getWriter().write(content);
            response.getWriter().flush();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static HttpServletResponse initResponseHeader(final String contentType, final String... headers) {

        String encoding = DEFAULT_ENCODING;
        boolean noCache = DEFAULT_NOCACHE;
        for (String header : headers) {
            String headerName = StringUtils.substringBefore(header, ":");
            String headerValue = StringUtils.substringAfter(header, ":");

            if (StringUtils.equalsIgnoreCase(headerName, HEADER_ENCODING)) {
                encoding = headerValue;
            } else if (StringUtils.equalsIgnoreCase(headerName, HEADER_NOCACHE)) {
                noCache = Boolean.parseBoolean(headerValue);
            } else {
                throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
            }
        }

        HttpServletResponse response = ServletActionContext.getResponse();

        String fullContentType = contentType + ";charset=" + encoding;
        response.setContentType(fullContentType);
        if (noCache) {
            //Http 1.0 header
            response.setDateHeader("Expires", 0);
            response.addHeader("Pragma", "no-cache");
            //Http 1.1 header
            response.setHeader("Cache-Control", "no-cache");
        }

        return response;
    }
}
