package com.hong.service;

import com.hong.domain.City;

/**
 * Created by hong on 2017/5/4.
 */
public interface CityService {

    City findOneCity(Integer id);

    int saveCity(City city);

    int modifyCity(City city);

    int deleteCity(Integer id);
}
