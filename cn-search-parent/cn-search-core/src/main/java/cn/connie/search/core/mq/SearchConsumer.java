package cn.connie.search.core.mq;

import cn.kafka.lib.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("searchConsumer")
@Transactional(rollbackFor = Throwable.class)
public class SearchConsumer extends Consumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchConsumer.class);

    @Override
    protected void process(String s) throws Exception {
        // 处理业务
        LOGGER.info(s);
    }

    @Override
    protected String getTopic() {
        return "CN-SEARCH-CORE";
    }

    @Override
    protected String getGroupId() {
        return "CN-SEARCH-CORE";
    }
}
