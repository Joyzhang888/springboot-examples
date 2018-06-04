package com.hong.service.impl;

import com.hong.domain.City;
import com.hong.mapper.CityMapper;
import com.hong.service.CityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by hong on 2017/5/4.
 */
@Service
public class CityServiceImpl implements CityService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    private final String KEY_PREFIX = "city_";


    /**
     * 获取城市逻辑：
     * 如果缓存存在，从缓存中获取城市信息
     * 如果缓存不存在，从 DB 中获取城市信息，然后插入缓存
     */
    @Override
    public City findOneCity(Integer id) {
        ValueOperations<String, City> valueOperations = redisTemplate.opsForValue();

        //缓存存在
        String key = KEY_PREFIX + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            City city = valueOperations.get(key);
            logger.info("CityServiceImpl.findOneCity() : 从缓存中获取了城市 >> " + city.toString());
            return city;
        }

        //从mysql 数据库中获取数据
        City city = cityMapper.selectByPrimaryKey(id);

        //存入缓存中.
        valueOperations.set(key, city, 10, TimeUnit.SECONDS);
        logger.info("CityServiceImpl.findOneCity() : 城市加入了缓存 >> " + city.toString());
        return city;
    }

    @Override
    public int saveCity(City city) {
        return cityMapper.insert(city);
    }

    @Override
    public int modifyCity(City city) {
        //更新DB中的数据
        int count = cityMapper.updateByPrimaryKey(city);

        //如果缓存中存在，移除。
        String key = KEY_PREFIX + city.getId();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            logger.info("CityServiceImpl.modifyCity 从缓存中移除了城市" + city.toString());
        }
        return count;
    }

    @Override
    public int deleteCity(Integer id) {
        //删除DB中的数据
        int count = cityMapper.deleteByPrimaryKey(id);

        //如果缓存中存在，移除。
        String key = KEY_PREFIX + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            logger.info("CityServiceImpl.modifyCity 从缓存中移除了城市 ID:" + id);
        }
        return count;
    }


}
