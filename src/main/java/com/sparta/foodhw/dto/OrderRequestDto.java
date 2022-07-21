package com.sparta.foodhw.dto;

import com.sparta.foodhw.model.Food;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRequestDto {
    private Long restaurantId;
    private List<OrderFoodDto> foods;
}
