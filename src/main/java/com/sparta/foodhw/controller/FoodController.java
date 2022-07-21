package com.sparta.foodhw.controller;

import com.sparta.foodhw.dto.FoodRequestDto;
import com.sparta.foodhw.dto.RestaurantRequestDto;
import com.sparta.foodhw.model.Food;
import com.sparta.foodhw.model.Restaurant;
import com.sparta.foodhw.service.FoodService;
import com.sparta.foodhw.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //json값으로 반환된다는 뜻
//@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @Autowired //생성자 1개일때만 생략가능 //21~23: DI //Autowired:Bean 사용하기위해 씀
    public FoodController(FoodService foodService){ //컨트롤러에서 서비스를 주입받음.(굉장히 중요함) = 이것 자체가 DI(의존성 주입)
        this.foodService = foodService;
    }
    //requestDto를 사용하는 이유: RequestBody안에 들어있는 값들을 사용하기 위해
    @PostMapping("/restaurant/{restaurantId}/food/register") //밑에 Dto가 List인 이유는 ReqestBody sample (api명세서)이 List형태기 때문임.
    public void registerFood(@PathVariable Restaurant restaurantId, @RequestBody List<FoodRequestDto> requestDto){
        foodService.registerFood(requestDto, restaurantId); //서비스에 매개변수 값을 넘겨줌
    }

    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> getFood(@PathVariable Restaurant restaurantId) {
        return foodService.getFood(restaurantId); //결국 마지막에 서비스에서 컨트롤러로 넘어와서 클라이언트에게 보여준다.
    }
}

