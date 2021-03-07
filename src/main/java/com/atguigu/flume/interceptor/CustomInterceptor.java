package com.atguigu.flume.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;

//在该案例中，我们以端口数据模拟日志，以数字（单个）和字母（单个）模拟不同类型
//        的日志，我们需要自定义 interceptor 区分数字和字母，将其分别发往不同的分析系统
//        （Channel）。
//2.定义 CustomInterceptor 类并实现 Interceptor 接口。
public class CustomInterceptor implements Interceptor {
    public void initialize() {

    }

    public Event intercept(Event event) {
        byte[] body = event.getBody();
        if (body[0] <= 'z' && body[0] >= 'a') {
            event.getHeaders().put("type", "letter");
        } else if (body[0] >= '0' && body[0] <= '9') {
            event.getHeaders().put("type", "number");
        }
        return event;
    }

    public List<Event> intercept(List<Event> events) {
        for (Event event : events) {
            //在使用foreach遍历list时候，可以修改元素，但是不能替换list中的元素，示例如下：
            //示例： https://blog.csdn.net/zuoyexingchennn/article/details/46969821/
            intercept(event);
        }
        return events;
    }

    public void close() {

    }

    //使用builder，返回CustomInterceptor
    public static class Builder implements Interceptor.Builder {

        public Interceptor build() {
            return new CustomInterceptor();
        }

        public void configure(Context context) {

        }
    }
}
