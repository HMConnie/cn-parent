package cn.connie.user.core;

import cn.connie.common.model.PushMessage;
import cn.connie.job.service.PushRemoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml", "classpath:redis-config.xml"})
public class DBTests {

    @Test
    public void testApollo() throws Exception {
    }

    @Resource
    private PushRemoteService pushRemoteService;


    @Test
    public void testPush() throws Exception {
        pushRemoteService.push("ab29ff542ba940eda89d7ba6202e69e8", null, "22222", PushMessage.PushMessageType.OTHER, null);
    }


}
