package cn.connie.search.core.impl;

import cn.connie.common.type.SearchBusinessType;
import cn.connie.search.criteria.SearchCriteria;
import cn.connie.search.from.SearchFrom;
import cn.connie.search.service.SearchService;
import cn.connie.search.to.SearchTO;
import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class SearchServiceImpl implements SearchService {

    @Value("${elasticsearch.host}")
    private String ELASTICSEARCH_HOST;
    @Value("${elasticsearch.port}")
    private String ELASTICSEARCH_PORT;
    @Value("${elasticsearch.cluster.name}")
    private String ELASTICSEARCH_CLUSTER_NAME;
    @Value("${elasticsearch.node.name}")
    private String ELASTICSEARCH_NODE_NAME;


    static PreBuiltTransportClient preBuiltTransportClient = null;


    static TransportClient transportClient = null;


    TransportClient getClient() throws UnknownHostException {
        if (transportClient == null) {
            Settings settings = Settings.builder()
                    .put("cluster.name", ELASTICSEARCH_CLUSTER_NAME)
                    .put("node.name", ELASTICSEARCH_NODE_NAME)
                    .build();
            preBuiltTransportClient = new PreBuiltTransportClient(settings);
            InetAddress inetAddress = InetAddress.getByName(ELASTICSEARCH_HOST);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddress, Integer.parseInt(ELASTICSEARCH_PORT));
            TransportAddress transportAddress = new TransportAddress(inetSocketAddress);
            transportClient = preBuiltTransportClient.addTransportAddress(transportAddress);
        }
        return transportClient;
    }

    @Override
    public SearchTO query(SearchCriteria searchCriteria) throws UnknownHostException {
        SearchTO dataEntity = new SearchTO();
        List list = new ArrayList();
        TransportClient client = getClient();

        SearchRequestBuilder searchRequestBuilder = client.prepareSearch("search-data");

        SearchBusinessType searchBusinessType = searchCriteria.getSearchBusinessType();
        BoolQueryBuilder must = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("key", searchCriteria.getKey()))
                .must(QueryBuilders.matchQuery("SearchType", searchBusinessType.toString()));

        SearchResponse searchResponse = searchRequestBuilder
                .addSort("createTime", SortOrder.DESC)
                .setQuery(must)
                .setFrom(searchCriteria.getOffset())
                .setSize(searchCriteria.getSize())
                .get();

        SearchHits hitss = searchResponse.getHits();
        dataEntity.setTotalHits(hitss.getTotalHits());
        SearchHit[] hits = hitss.getHits();
        for (SearchHit searchHit : hits) {
            Map<String, Object> source = searchHit.getSourceAsMap();
            Object data = source.get("data");
            list.add(JSONObject.parseObject(data.toString()));
        }
        dataEntity.setData(list);
        return dataEntity;
    }

    @Override
    public void insertOrUpdate(SearchFrom searchEntity) throws IOException {
        TransportClient client = getClient();
        XContentBuilder builder = XContentFactory.jsonBuilder().startObject()
                .field("@timestamp", "2000-01-01T00:00:00.000Z")
                .field("@version", "1")
                .field("ids", searchEntity.getId())
                .field("key", searchEntity.getKey())
                .field("SearchType", searchEntity.getSearchBusinessType())
                .field("data", searchEntity.getObject())
                .field("createTime", System.currentTimeMillis())
                .endObject();
        //插入数据

        IndexRequestBuilder search = client.prepareIndex("search-data", "Search",
                searchEntity.getSearchBusinessType().toString() + searchEntity.getId());
        IndexResponse indexResponse = search.setSource(builder).get();
        System.out.println("restStatus:" + indexResponse.status());
    }

    @Override
    public void delete(String id, SearchBusinessType searchBusinessType) throws UnknownHostException {
        TransportClient client = getClient();
        DeleteResponse deleteResponse = client
                .prepareDelete("search-data", "Search", searchBusinessType.toString() + id).get();
        System.out.println("restStatus:" + deleteResponse.status());
    }

}
