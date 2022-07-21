package com.sparta.foodhw.repository;

import com.sparta.foodhw.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
