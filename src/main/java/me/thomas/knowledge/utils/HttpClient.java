package me.thomas.knowledge.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 2015/10/18.
 */
public class HttpClient {

    /**
     * Get请求
     *
     * @param uri 资源路径
     * @return 响应内容
     */
    public static String get(String uri) {
        HttpGet httpGet = new HttpGet(uri);

        if (logger.isDebugEnabled()) {
            logger.debug(httpGet.getRequestLine().toString());
        }

        return execute(httpGet);
    }

    /**
     * Get请求
     *
     * @param uri 资源路径
     * @param params 请求参数
     * @return 响应内容
     */
    public static String get(String uri, Map<String, String> params) {
        boolean noQuestionMark = uri.indexOf('?') == -1;
        StringBuffer sb = new StringBuffer(uri);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (StringUtils.isNotEmpty(entry.getValue())) {
                    sb.append("&")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                }
            }
        }
        if (noQuestionMark) {
            int pos = uri.length();
            sb.replace(pos, pos + 1, "?");
        }
        HttpGet httpGet = new HttpGet(sb.toString());

        if (logger.isDebugEnabled()) {
            logger.debug(httpGet.getRequestLine().toString());
        }

        return execute(httpGet);
    }

    /**
     * Post请求
     *
     * @param uri 资源路径
     * @param params 请求参数
     * @return 响应内容
     */
    public static String post(String uri, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(uri);
        List<BasicNameValuePair> postData = new ArrayList<BasicNameValuePair>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (StringUtils.isNotEmpty(entry.getValue())) {
                    postData.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
        }
        HttpEntity reqEntity = new UrlEncodedFormEntity(postData, Charset.forName("UTF-8"));
        httpPost.setEntity(reqEntity);

        if (logger.isDebugEnabled()) {
            logger.debug(httpPost.getRequestLine().toString());
        }

        return execute(httpPost);
    }

    /**
     * 通过文件上传，服务器端可获取文件名
     *
     * @param uri 上传路径
     * @param file 文件
     * @return 响应内容
     */
    public static String upload(String uri, File file) {
        HttpPost httpPost = new HttpPost(uri);
        FileBody fileBody = new FileBody(file);
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("file", fileBody)
                .build();
        httpPost.setEntity(reqEntity);

        if (logger.isDebugEnabled()) {
            logger.debug(httpPost.getRequestLine().toString());
        }

        return execute(httpPost);
    }

    /**
     * 通过二进制流上传，服务器端获取不到文件名
     *
     * @param uri 上传路径
     * @param inputStream 文件流
     * @return 响应内容
     */
    public static String upload(String uri, InputStream inputStream) {
        HttpPost httpPost = new HttpPost(uri);
        HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addBinaryBody("file", inputStream)
                .build();
        httpPost.setEntity(reqEntity);

        if (logger.isDebugEnabled()) {
            logger.debug(httpPost.getRequestLine().toString());
        }

        return execute(httpPost);
    }

    public static String execute(HttpUriRequest request) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            if (logger.isDebugEnabled()) {
                logger.debug(response.getStatusLine().toString());
            }
            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode() ||
                    HttpStatus.SC_NOT_MODIFIED == response.getStatusLine().getStatusCode()) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseText = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
                    if (logger.isDebugEnabled()) {
                        logger.debug(request.getURI() + " response => {}", responseText);
                    }
                    return responseText;
                }
            }
            logger.warn(response.getStatusLine().getReasonPhrase());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // ignore
            }
        }

        return null;
    }

    private final static Logger logger = LoggerFactory.getLogger(HttpClient.class);

}