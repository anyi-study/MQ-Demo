package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("itcast.fanout");
    }
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanout.queue1");
    }
    @Bean
    public Binding fanoutBinding(FanoutExchange fanoutExchange, Queue fanoutQueue1){
        return BindingBuilder
                .bind(fanoutQueue1)
                .to(fanoutExchange);
    }
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }
    @Bean
    public Binding fanoutBinding2(FanoutExchange fanoutExchange, Queue fanoutQueue2){
        return BindingBuilder
                .bind(fanoutQueue2)
                .to(fanoutExchange);
    }
    /**
     * 对象传递消息
     */
    @Bean
    public Queue objectQueue(){
        return new Queue("object.queue");
    }
}
