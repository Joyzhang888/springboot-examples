package com.hong.elasticsearch.repository;

import com.alibaba.fastjson.JSONObject;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateRequestBuilder;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.elasticsearch.script.ScriptService;

import java.util.Map;
import java.util.UUID;

/**
 * @author tianhong
 * @Description ES的基础操作类
 * @date 2018/5/31 11:03
 */
public class ESRepository<T> {

	private static final Logger log = LoggerFactory.getLogger(ESRepository.class);

	@Autowired
	private TransportClient client;

	/**
	 * 创建索引
	 */
	public boolean buildIndex(String index) {
		if (!isIndexExist(index)) {
			log.info("Index is not exits!");
		}
		CreateIndexResponse buildIndexresponse = client.admin().indices().prepareCreate(index).execute().actionGet();
		log.info(" 创建索引的标志: " + buildIndexresponse.isAcknowledged());

		return buildIndexresponse.isAcknowledged();
	}
	
	 /**
     * 删除索引
     */
    public boolean deleteIndex(String index) {
        if (!isIndexExist(index)) {
            log.info(" 索引不存在 ！！！！！!");
        }
        DeleteIndexResponse diResponse = client.admin().indices().prepareDelete(index).execute().actionGet();
        if (diResponse.isAcknowledged()) {
        	log.info("删除索引**成功** index->>>>>>>" + index);
        } else {
        	log.info("删除索引**失败** index->>>>> " + index);
        }
        return diResponse.isAcknowledged();
    }
    
    /**
     * 根据ID查询数据
     */
    public Map<String, Object> searchById(String index, String type, String id) {
    	if(StringUtils.isEmpty(index)|| StringUtils.isEmpty(type) || StringUtils.isEmpty(id )) {
    		log.info(" 无法查询数据，缺唯一值!!!!!!! ");
    		return null;
    	}
        GetRequestBuilder getRequestBuilder = client.prepareGet(index, type, id);
        GetResponse getResponse = getRequestBuilder.execute().actionGet();
        return getResponse.getSource();
    }
    
    /**
     * 根据ID更新数据
     */
    public void updateById(JSONObject data, String index, String type, String id) {
    	if(StringUtils.isEmpty(index)|| StringUtils.isEmpty(type)|| StringUtils.isEmpty(id )) {
    		log.error(" 无法更新数据，缺唯一值!!!!!!! ");
    		return;
    	}
        UpdateRequest up = new UpdateRequest();
        up.index(index).type(type).id(id).doc(data);
        UpdateResponse response = client.update(up).actionGet();
        log.info("更新数据状态信息，status:{}", response.status().getStatus());
    }

	/**
	 * 根据ID更新数据
	 */
	public void updateById(Map<String,Object> data, String index, String type, String id) {
		if(StringUtils.isEmpty(index)|| StringUtils.isEmpty(type)|| StringUtils.isEmpty(id )) {
			log.error(" 无法更新数据，缺唯一值!!!!!!! ");
			return;
		}
		UpdateRequest up = new UpdateRequest();
		up.index(index).type(type).id(id).doc(data);
		UpdateResponse response = client.update(up).actionGet();
		log.info("更新数据状态信息，status:{}", response.status().getStatus());
	}

	/**
	 * 根据id 更新，使用script 语法字符串
	 */
	public void updateById(String scriptData, String index, String type, String id) {
		if(StringUtils.isEmpty(index)|| StringUtils.isEmpty(type)|| StringUtils.isEmpty(id )) {
			log.error(" 无法更新数据，缺唯一值!!!!!!! ");
			return;
		}
		Script script = new Script(scriptData);
		UpdateRequestBuilder response = client.prepareUpdate(index, type, id)
				.setScript(script);
		UpdateResponse updateResponse = response.get();
		log.info("更新数据状态信息，status:{}", updateResponse.status().getStatus());
	}

	/**
     * 添加数据
	 * 类型：JSONObject
     */
    public String insert(String index, String type,JSONObject data, String id) {
    	if(StringUtils.isEmpty(id)) {
    		id = UUID.randomUUID().toString();
    	}

        IndexResponse response = client.prepareIndex(index, type,id).setSource(data).get();
        log.info("insert 添加数据的状态:{}", response.status().getStatus());
        return response.getId();
    }

	/**
	 * 添加数据
	 * 类型：Map
	 */
	public String insert(String index, String type,Map<String,Object> data, String id) {
		if(StringUtils.isEmpty(id)) {
			id = UUID.randomUUID().toString();
		}

		IndexResponse response = client.prepareIndex(index, type,id).setSource(data).get();
		log.info("insert 添加数据的状态:{}", response.status().getStatus());
		return response.getId();
	}

	/**
	 * 添加数据
	 * 类型：json 字符串
	 */
	public String insert(String index, String type, String json,String id) {
		if(StringUtils.isEmpty(id)) {
			id = UUID.randomUUID().toString();
		}
		IndexResponse response = client.prepareIndex(index, type,id).setSource(json,XContentType.JSON).get();
		log.info("insert 添加数据的状态:{}", response.status().getStatus());
		return response.getId();
	}
    
    /**
     * 通过ID删除数据
     */
    public void delById(String index, String type, String id) {

    	if(StringUtils.isEmpty(index) || StringUtils.isEmpty(type) || StringUtils.isEmpty(id)) {
    		log.info(" 无法删除数据，缺唯一值!!!!!!! ");
    		return;
    	}
        DeleteResponse response = client.prepareDelete(index, type, id).execute().actionGet();
        log.info("删除数据状态，status-->>>>{},", response.status().getStatus());


    }

	/**
	 * 判断索引是否存在
	 */
	public boolean isIndexExist(String index) {
		if(StringUtils.isEmpty(index)){
			log.info("索引参数为空.");
			return false;
		}
		IndicesExistsResponse iep = client.admin().indices().exists(new IndicesExistsRequest(index)).actionGet();
		if (iep.isExists()) {
			log.info("此索引 [" + index + "] 已经在ES集群里存在");
		} else {
			log.info(" 没有此索引 [" + index + "] ");
		}
		return iep.isExists();
	}



}
