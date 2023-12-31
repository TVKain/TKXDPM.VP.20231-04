package com.itep.hust.aimsgroup.service.dao;

import com.itep.hust.aimsgroup.model.order.OrderMedia;
import javafx.util.Pair;

import java.util.List;

public interface OrderMediaDao extends Dao<OrderMedia, Pair<Integer, Integer>> {
    List<OrderMedia> getByOrderId(Integer id);
}
