package com.sparta.foodhw.repository;

import com.sparta.foodhw.model.Food;
import com.sparta.foodhw.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurant(Restaurant restaurantId); //restaurantId는 Restaurant 클래스의 id임.


}
