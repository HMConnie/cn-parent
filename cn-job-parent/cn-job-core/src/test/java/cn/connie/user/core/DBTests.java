package cn.connie.user.core;

import cn.connie.common.model.PushMessage;
import cn.connie.job.service.PushRemoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml", "classpath:redis-config.xml"})
public class DBTests {

    @Test
    public void testApollo() throws Exception {
    }

    @Resource
    private PushRemoteService pushRemoteService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testPush() throws Exception {
        BoundListOperations<String, String> listOps = redisTemplate.boundListOps("PUSH_STORE_KEY");
        listOps.rightPush("1");
        listOps.rightPush("2");
        listOps.rightPush("3");
//        String s1 = listOps.leftPop();
//        String s2 = listOps.leftPop();
//        String s3 = listOps.leftPop();
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
        Long size = listOps.size();
        for (int i = 0; i < size; i++) {
            String s = listOps.leftPop();
            System.out.println(s);
        }

    }


}
