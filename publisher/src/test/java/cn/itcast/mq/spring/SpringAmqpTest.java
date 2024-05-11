package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Test
    public void testSendMessage2SimpleQueue() throws InterruptedException {
        // 1.发送消息
        String queryName = "simple.queue";
        String message = "Hello, Spring Amqp!";
        rabbitTemplate.convertAndSend(queryName, message);
    }
    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        // 1.发送消息
        String queryName = "simple.queue";
        String message = "Hello, message=";
        for (int i = 0; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queryName, message+i);
            Thread.sleep(20);
        }
    }

    /**
     * fanout
     */
    @Test
    public void testSendFanoutExchange(){
//        交换机名称
        String exchangeName = "itcast.fanout";
//        笑死
        String message = "Hello, CodeJ";
//        发送消息
        rabbitTemplate.convertAndSend(exchangeName, "", message);

    }

    /**
     * direct
     */
    @Test
    public void testSendDirectExchange() {
        // 交换机名称
        String exchangeName = "itcast.direct";
        // 消息
        String message = "红色警报！日本乱排核废水，导致海洋生物变异，惊现哥斯拉！";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "blue", message);
    }

    /**
     * topicExchange
     */
    @Test
    public void testSendTopicExchange() {
        // 交换机名称
        String exchangeName = "itcast.topic";
        // 消息
        String message = "喜报！孙悟空大战哥斯拉，胜!";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName, "china.news", message);
    }
    @Test
    public void testSendMap() throws InterruptedException {
        // 准备消息
        Map<String,Object> msg = new HashMap<>();
        msg.put("name", "Jack");
        msg.put("age", 21);
        // 发送消息
        rabbitTemplate.convertAndSend("object.queue","", msg);
    }
}
