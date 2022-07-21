package com.sparta.foodhw.dto;

import lombok.Getter;

@Getter //private 변수를 다른 클래스에서 get을 통해서 가져오기 위해
public class FoodRequestDto {
    private String name;
    private int price;
}
