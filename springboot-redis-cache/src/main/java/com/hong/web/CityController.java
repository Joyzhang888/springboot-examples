package com.hong.web;

import com.hong.domain.City;
import com.hong.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hong on 2017/5/4.
 */
@RestController
public class CityController {

    @Autowired
    private CityService cityService;


    /**
     * 根据城市ID 获取城市信息
     * @param id
     * @return
     */
    @GetMapping("/api/city/{id}")
    public City findOneCity(@PathVariable("id") Integer id){
         return  cityService.findOneCity(id);
    }

    /**
     * 新增一个城市信息
     * @param city
     * @return
     */
    @PostMapping("/api/city")
    public int saveCity(City city){
        return  cityService.saveCity(city);
    }

    /**
     * 更新一个城市信息
     * @param city
     * @return
     */
    @PutMapping("/api/city")
    public int modifyCity(City city){
        return  cityService.modifyCity(city);
    }

    /**
     *  删除一个城市信息
     * @return
     */
    @DeleteMapping("/api/city/{id}")
    public int deleteCity(@PathVariable("id") Integer id){
        return cityService.deleteCity(id);
    }
}
