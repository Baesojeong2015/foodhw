package com.sparta.foodhw.model;

import com.sparta.foodhw.dto.FoodRequestDto;
import com.sparta.foodhw.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity //직접 db로 들어가는 클래스
public class Food {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id //private 키
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RESTAURANT_ID", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;




    public Food(FoodRequestDto requestDto, Restaurant restaurantId){
        this.restaurant = restaurantId; //주황색 = 변수 //restaurantId를 자기 마음대로 설정 (변수)
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
    }
}