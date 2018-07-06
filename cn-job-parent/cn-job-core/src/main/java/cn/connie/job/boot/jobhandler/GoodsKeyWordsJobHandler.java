package cn.connie.job.boot.jobhandler;

import cn.connie.common.type.SearchBusinessType;
import cn.connie.goods.service.GoodsService;
import cn.connie.goods.to.GoodsCategoryTO;
import cn.connie.goods.to.GoodsTO;
import cn.connie.search.from.SearchFrom;
import cn.connie.search.service.SearchService;
import com.sgcai.zookeeper.lib.DistributedLock;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@JobHandler(value = "goodsKeyWordsJobHandler")
@Component
public class GoodsKeyWordsJobHandler extends IJobHandler {

    private final Logger logger = LoggerFactory.getLogger(GoodsKeyWordsJobHandler.class);

    @Autowired
    private SearchService searchService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private DistributedLock distributedLock;

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        logger.info("start execute goodsKeyWordsJobHandler.");
        boolean isLock = false;//是否获取到分布式锁
        try {
            isLock = distributedLock.getLock(this.getClass().getName());
            if (isLock) {
                List<GoodsCategoryTO> goodsCategoryByTimeDesc = goodsService.getGoodsCategoryByTimeDesc();
                for (GoodsCategoryTO goodsCategoryTO : goodsCategoryByTimeDesc) {
                    List<GoodsTO> goodsTOS = goodsService.getGoodsByCategoryWithTimeDesc(goodsCategoryTO.getId());
                    for (GoodsTO goodsTO : goodsTOS) {
                        SearchFrom searchFrom = new SearchFrom();
                        searchFrom.setId(goodsTO.getId());
                        searchFrom.setKey(goodsTO.getTitle());
                        searchFrom.setSearchBusinessType(SearchBusinessType.GoodsType);
                        searchFrom.setObject(goodsTO);
                        searchService.insertOrUpdate(searchFrom);
                    }
                }
            }
        } catch (Exception e) {
            logger.info("search insert failed", e);
            throw e;
        } finally {
            if (isLock) {
                distributedLock.releaseLock();
            }
        }
        return ReturnT.SUCCESS;
    }
}
