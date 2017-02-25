package me.thomas.knowledge.concurrent.task;

import me.thomas.knowledge.utils.HttpClient;

/**
 * @author zhaoxinsheng
 * @date 8/27/16.
 */
public class CurlTask implements Task {

    private String url;

    public CurlTask(String url) {
        this.url = url;
    }

    @Override
    public boolean execute() {
        String result = HttpClient.get(url);
        System.out.println(result);
        return true;
    }
}
