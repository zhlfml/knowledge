package me.thomas.knowledge.concurrent.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import me.thomas.knowledge.utils.UUIDUtil;

/**
 * @author zhaoxinsheng
 * @date 8/27/16.
 */
public class CurlTask implements Task {

    private String url;
    private RestTemplate restTemplate = new RestTemplate();

    public CurlTask(String url) {
        this.url = url;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        restTemplate.getMessageConverters().forEach(httpMessageConverter -> {
            if (httpMessageConverter instanceof AbstractJackson2HttpMessageConverter) {
                ((AbstractJackson2HttpMessageConverter) httpMessageConverter).setObjectMapper(objectMapper);
            }
        });
    }

    @Override
    public boolean execute() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Feedback> entity = new HttpEntity<>(new Feedback(UUIDUtil.genUUID(), UUIDUtil.genUUID() + "@patsnap.com", ""), headers);
        String response = restTemplate.postForEntity(url, entity, String.class).getBody();
        System.out.println(response);
        return true;
    }

    private static class Feedback {
        private String userId;
        private String email;
        private String feedbackContent;

        public Feedback(String userId, String email, String feedbackContent) {
            this.userId = userId;
            this.email = email;
            this.feedbackContent = feedbackContent;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFeedbackContent() {
            return feedbackContent;
        }

        public void setFeedbackContent(String feedbackContent) {
            this.feedbackContent = feedbackContent;
        }
    }
}
