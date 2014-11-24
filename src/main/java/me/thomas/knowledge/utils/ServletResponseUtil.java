package me.thomas.knowledge.utils;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletResponseUtil {

    public static void write(HttpServletResponse response, byte[] bytes) throws IOException {
        write(response, bytes, 0, 0);
    }

    public static void write(HttpServletResponse response, byte[] bytes, int offset, int contentLength)
            throws IOException {

        // LEP-3122
        if (!response.isCommitted()) {

            // LEP-536
            if (contentLength == 0) {
                contentLength = bytes.length;
            }

            response.setContentLength(contentLength);

            response.flushBuffer();

            ServletOutputStream servletOutputStream = response.getOutputStream();

            servletOutputStream.write(bytes, offset, contentLength);
        }

    }

    public static void sendFile(HttpServletRequest request, HttpServletResponse response, String fileName,
            byte[] bytes, String contentType) throws IOException {

        setHeaders(request, response, fileName, contentType);

        write(response, bytes);
    }

    protected static void setHeaders(HttpServletRequest request, HttpServletResponse response, String fileName,
            String contentType) {

        // LEP-2201
        if (Validator.isNotNull(contentType)) {
            response.setContentType(contentType);
        }

        response.setHeader(HttpHeaders.CACHE_CONTROL, HttpHeaders.CACHE_CONTROL_PUBLIC_VALUE);
        response.setHeader(HttpHeaders.PRAGMA, HttpHeaders.PRAGMA_PUBLIC_VALUE);

        if (Validator.isNotNull(fileName)) {
            String contentDisposition = "attachment; filename=\"" + fileName + "\"";

            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);
        }
    }

}
