package com.sparta.foodhw.model;

import com.sparta.foodhw.dto.RestaurantRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Entity //직접적으로 db에 들어가는 클래스
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id //고유의값임, private key
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int minOrderPrice;

    @Column(nullable = false)
    private int deliveryFee;

    public Restaurant(String name, int minOrderPrice, int deliveryFee){
        this.name = name;
        this.minOrderPrice = minOrderPrice;
        this.deliveryFee = deliveryFee;
    }
    public Restaurant(RestaurantRequestDto requestDto){ //생성자(return 타입이 없으면 생성자임)
        this.name = requestDto.getName(); //this.name은 이 클래스(레스토랑)의 name
        this.minOrderPrice = requestDto.getMinOrderPrice();
        this.deliveryFee = requestDto.getDeliveryFee();
    }

////    public Restaurant(RestaurantRequestDto requestDto, String name) {
////        this.name = requestDto.getName();
////        this.minOrderPrice = requestDto.getMinOrderPrice();
////        this.deliveryFee = requestDto.getDeliveryFee();
//    }
}
