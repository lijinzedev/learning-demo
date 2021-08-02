package com.curiosity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class EsApiApplicationTests {
    @Resource
    RestHighLevelClient restHighLevelClient;

    @Test
    void testCreateIndex() throws IOException {
        // 创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("boot_index");
        // 执行请求
        final CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    @Test
        // 获取索引判断是否存在
    void getIndex() throws IOException {
        // 获取索引请求
        GetIndexRequest request = new GetIndexRequest("boot_index");
        // 执行请求
        final boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
        // 删除索引
    void deletreIndex() throws IOException {
        // 获取索引请求
        DeleteIndexRequest request = new DeleteIndexRequest("boot_index");
        // 执行请求
        final AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    @Test
        //  创建文档
    void createDecument() throws IOException {


    }

    @Test
        //  获取文档
    void getDecument() throws IOException {

        // 创建请求
        GetRequest request = new GetRequest("boot_index", "1");
        // 不获取返回的_source 的上下文
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        final boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
        //  获取文档
    void getDecumentSource() throws IOException {

        // 创建请求
        GetRequest request = new GetRequest("boot_index", "1");
        final GetResponse documentFields = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSourceAsString());
    }

    @Test
        //  更新
    void update() throws IOException {

        // 创建请求
        UpdateRequest request = new UpdateRequest("boot_index", "1");
        User user = new User();
        user.setUser("纳鲁托");
        user.setAge(17);
        ObjectMapper mapper = new ObjectMapper();
        final String string = mapper.writeValueAsString(user);
        request.doc(string, XContentType.JSON);
        final UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(update.getGetResult());
    }

    @Test
        //  删除
    void delete() throws IOException {

        // 创建请求
        DeleteRequest request = new DeleteRequest("boot_index", "1");

        final DeleteResponse delete = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.status());
    }

    @Test
        //  批量导入
    void taskBulkRequest() throws IOException {

        // 创建请求
        BulkRequest request = new BulkRequest("boot_index");
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new User("测试" + i, i));
        }
        ObjectMapper mapper = new ObjectMapper();

        for (int i = 0; i < list.size(); i++) {
            request.add(new IndexRequest("boot_index").id(i + "").
                    source(mapper.writeValueAsString(list.get(i)), XContentType.JSON));

        }
        final BulkResponse bulk = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        System.out.println(bulk.status());
    }

    @Test
        //  查询
    void query() throws IOException {
        SearchRequest searchRequest = new SearchRequest("boot_index");
        SearchSourceBuilder searchRequestBuilder = new SearchSourceBuilder();
        // 精确
        final TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("user", "测试1");
        // 匹配所有
        final MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchRequestBuilder.query(matchAllQueryBuilder);
        searchRequest.source(searchRequestBuilder);
        final SearchResponse search = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        final String s = Arrays.stream(search.getHits().getHits()).collect(Collectors.toList()).toString();
        System.out.println(s);
    }


}
