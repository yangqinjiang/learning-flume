package com.atguigu.flume;

import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//使用 flume 接收数据，并在 Sink 端给每条数据添加前缀和后缀，输出到控制台。前后
//        缀可在 flume 任务配置文件中配置
public class MySink extends AbstractSink implements Configurable {

    //创建Logger对象
    private static final Logger LOG = LoggerFactory.getLogger(AbstractSink.class);
    private String prefix;
    private String suffix;

    public void configure(Context context) {
        //读取配置文件内容,有默认值
        prefix = context.getString("prefix", "hello:");
        //读取配置文件内容,无默认值
        suffix = context.getString("suffix");
    }

    public Status process() throws EventDeliveryException {
        //声明返回值状态信息
        Status status;
        //获取当前sink绑定的channel
        Channel ch = getChannel();
        //获取事务
        Transaction txn = ch.getTransaction();
        //声明事件
        Event event;
        //开启事务
        txn.begin();
        //读取channel中的事件

        try {
            event = ch.take();
            if (event != null) {
                //处理事件（打印）
                LOG.info(prefix + new String(event.getBody()) + suffix);
            }
            //事务提交
            txn.commit();
            status = Status.READY;
        } catch (Exception e) {
            //遇到异常，事务回滚
            txn.rollback();
            status = Status.BACKOFF;
        } finally {
            //关闭事务
            txn.close();
        }
        return status;
    }


}
