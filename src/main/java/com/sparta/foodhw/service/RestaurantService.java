package com.sparta.foodhw.service;

import com.sparta.foodhw.dto.RestaurantRequestDto;
import com.sparta.foodhw.model.Restaurant;
import com.sparta.foodhw.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository; //서비스에서 레파지토리를 주입받는다.


    @Transactional //findAll같은 거를 한번에 처리해줌
    public Restaurant registerRestaurant(RestaurantRequestDto requestDto){ //레스토랑 타입을 리턴해줌^^
        int minOrderPrice = requestDto.getMinOrderPrice(); //requestDto에는 requestbody에서 받아온값이 있음

        if (!(minOrderPrice >= 1000 && minOrderPrice <= 100000)){
            throw new IllegalArgumentException("최소주문은 1,000원 ~ 100,000원 사이에서 가능합니다.");
        }

        if(minOrderPrice % 100 != 0){
            throw new IllegalArgumentException("값은 100원 단위로만 입력 가능합니다.");
        }

        int deliveryFee = requestDto.getDeliveryFee();
        if (!(deliveryFee >= 0 && deliveryFee <= 10000)){
            throw new IllegalArgumentException("배달비는 0원 ~ 10,000원 사이에서 가능합니다.");
        }

        if(deliveryFee % 500 != 0){
            throw new IllegalArgumentException("값은 500원 단위로만 입력 가능합니다.");
        }

        Restaurant restaurant = new Restaurant(requestDto); //레스토랑 클래스 32~35번줄을 사용, 리퀘스트바디에서 받아온 값을 리퀘스트디티오에 넣어준 값을 레스토랑 값에 넣어줌

        return restaurantRepository.save(restaurant); //.save, findbyid 등 Jpa 기본 문법임ㅇㅇ
                                                        //restaurant값을 디비에 저장해라

    }

    public List<Restaurant> getRestaurant(){
        return restaurantRepository.findAll(); //디비에 저장했던값 다끌어와라
    }


}
