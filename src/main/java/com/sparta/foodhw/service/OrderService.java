package com.sparta.foodhw.service;

import com.sparta.foodhw.dto.OrderFoodDto;
import com.sparta.foodhw.dto.OrderFoodResponseDto;
import com.sparta.foodhw.dto.OrderRequestDto;
import com.sparta.foodhw.dto.OrderResponseDto;
import com.sparta.foodhw.model.Food;
import com.sparta.foodhw.model.Order;
import com.sparta.foodhw.model.OrderFood;
import com.sparta.foodhw.model.Restaurant;
import com.sparta.foodhw.repository.FoodRepository;
import com.sparta.foodhw.repository.OrderRepository;
import com.sparta.foodhw.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    private final FoodRepository foodRepository;

    //@Requiredargsconstructor 어노테이션이 이거 쓰는거 대신해줌
   public OrderService(OrderRepository orderRepository, RestaurantRepository restaurantRepository,
                       FoodRepository foodRepository){
       this.orderRepository = orderRepository;
       this.restaurantRepository = restaurantRepository;
       this.foodRepository = foodRepository;
    }

    public OrderResponseDto order(OrderRequestDto dto){
       Long restaurantId = dto.getRestaurantId();
       OrderResponseDto orderResponseDto = new OrderResponseDto();

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new IllegalArgumentException("없는 식당입니다."));

        orderResponseDto.setRestaurantName(restaurant.getName());
        orderResponseDto.setDeliveryFee(restaurant.getDeliveryFee());

        List<OrderFoodDto> orderFoodDtoList = dto.getFoods();

        Order order = new Order();
        order.setRestaurant(restaurant);
        List<OrderFood> orderFoodList = new ArrayList<>(); //Entity에 입력할 List
        List<OrderFoodResponseDto> foodResponseDtoList = new ArrayList<>(); //반환할 List

        int totalPrice = 0;

        for(OrderFoodDto orderFoodDto : orderFoodDtoList){
            Long foodId = orderFoodDto.getId();
            int quantity = orderFoodDto.getQuantity();

            Food food = foodRepository.findById(foodId)
                    .orElseThrow(()->new IllegalArgumentException("없는 음식입니다."));

            int foodPrice = food.getPrice() * quantity;

           OrderFood orderFood = new OrderFood(food, order, quantity);

            orderFoodList.add(orderFood);
            foodResponseDtoList.add(new OrderFoodResponseDto())
        }


        order.setOrderFoods(orderFoodList);

        orderRepository.save(order);

        return

    }
}
