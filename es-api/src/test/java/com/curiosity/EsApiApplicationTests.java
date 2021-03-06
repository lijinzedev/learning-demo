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
        // ??????????????????
        CreateIndexRequest request = new CreateIndexRequest("boot_index");
        // ????????????
        final CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    @Test
        // ??????????????????????????????
    void getIndex() throws IOException {
        // ??????????????????
        GetIndexRequest request = new GetIndexRequest("boot_index");
        // ????????????
        final boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
        // ????????????
    void deletreIndex() throws IOException {
        // ??????????????????
        DeleteIndexRequest request = new DeleteIndexRequest("boot_index");
        // ????????????
        final AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    @Test
        //  ????????????
    void createDecument() throws IOException {


    }

    @Test
        //  ????????????
    void getDecument() throws IOException {

        // ????????????
        GetRequest request = new GetRequest("boot_index", "1");
        // ??????????????????_source ????????????
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        final boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
        //  ????????????
    void getDecumentSource() throws IOException {

        // ????????????
        GetRequest request = new GetRequest("boot_index", "1");
        final GetResponse documentFields = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(documentFields.getSourceAsString());
    }

    @Test
        //  ??????
    void update() throws IOException {

        // ????????????
        UpdateRequest request = new UpdateRequest("boot_index", "1");
        User user = new User();
        user.setUser("?????????");
        user.setAge(17);
        ObjectMapper mapper = new ObjectMapper();
        final String string = mapper.writeValueAsString(user);
        request.doc(string, XContentType.JSON);
        final UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(update.getGetResult());
    }

    @Test
        //  ??????
    void delete() throws IOException {

        // ????????????
        DeleteRequest request = new DeleteRequest("boot_index", "1");

        final DeleteResponse delete = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.status());
    }

    @Test
        //  ????????????
    void taskBulkRequest() throws IOException {

        // ????????????
        BulkRequest request = new BulkRequest("boot_index");
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new User("??????" + i, i));
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
        //  ??????
    void query() throws IOException {
        SearchRequest searchRequest = new SearchRequest("boot_index");
        SearchSourceBuilder searchRequestBuilder = new SearchSourceBuilder();
        // ??????
        final TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("user", "??????1");
        // ????????????
        final MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        searchRequestBuilder.query(matchAllQueryBuilder);
        searchRequest.source(searchRequestBuilder);
        final SearchResponse search = restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);
        final String s = Arrays.stream(search.getHits().getHits()).collect(Collectors.toList()).toString();
        System.out.println(s);
    }


}
