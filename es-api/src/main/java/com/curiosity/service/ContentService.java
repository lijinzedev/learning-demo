package com.curiosity.service;

import com.alibaba.fastjson.JSON;
import com.curiosity.pojo.Content;
import com.curiosity.utils.HtmlParseUtil;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {
    private final RestHighLevelClient restHighLevelClient;

    public boolean parseContent(String keyWord) throws Exception {
        final List<Content> contents = HtmlParseUtil.parseJD(keyWord);
        BulkRequest bulkRequest = new BulkRequest();
        for (int i = 0; i < contents.size(); i++) {
            final IndexRequest request = new IndexRequest("boot_index");
            request.id("" + i);
            request.source(JSON.toJSONString(contents.get(i)), XContentType.JSON);
            bulkRequest.add(request);
        }
        final BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        final boolean b = bulk.hasFailures();
        return !b;
    }

    //2获取这些数据实现搜索功能
    public List<Map<String, Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException {
        if (pageNo < 1) {
            pageNo = 1;
        }
        SearchRequest request = new SearchRequest("boot_index");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.from(pageNo);
        builder.size(pageSize);
        final HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false);

        builder.highlighter(highlightBuilder);
        final TermQueryBuilder title = QueryBuilders.termQuery("title", keyword);
        builder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        builder.query(title);
        request.source(builder);
        final SearchResponse search = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        final SearchHit[] hits = search.getHits().getHits();
        List<Map<String, Object>> list = new ArrayList<>();
        Arrays.stream(hits).forEach(documentFields -> {
            final Map<String, HighlightField> highlightFields = documentFields.getHighlightFields();
            final Map<String, Object> sourceAsMap = documentFields.getSourceAsMap();
            final HighlightField title1 = highlightFields.get("title");
            if (title1 != null) {
                final Text[] fragments = title1.getFragments();
                String n_title = "";
                for (Text text : fragments) {
                    n_title += text;
                }
                sourceAsMap.put("title", n_title);
            }
            list.add(sourceAsMap);
        });
        return list;
    }
}
