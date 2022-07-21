package com.sparta.foodhw.dto;

import lombok.Data;

import java.util.List;

@Data //response니까 set도 가능
public class OrderResponseDto {

    private String restaurantName;
    private List<OrderFoodResponseDto> foods;
    private int deliveryFee;
    private int totalPrice;
}
