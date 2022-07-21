package com.sparta.foodhw.controller;

import com.sparta.foodhw.dto.RestaurantRequestDto;
import com.sparta.foodhw.model.Restaurant;
import com.sparta.foodhw.repository.RestaurantRepository;
import com.sparta.foodhw.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService; //컨트롤러에서 서비스를 주입받는다. 서비스->컨트롤러

    @PostMapping("/restaurant/register") //@RequestBody : 클라이언트에서 받아온다는 뚯
    public Restaurant registerRestaurant(@RequestBody RestaurantRequestDto requestDto){
        return restaurantService.registerRestaurant(requestDto);

    }
    @GetMapping("/restaurants")
    public List<Restaurant> getRestaurant() {

        return restaurantService.getRestaurant();
    }
    //response값이 있으니까 return을 받는다. 그리고 List를 사용하는 이유는 response값이 배열로 되어있어서 그럼
}
