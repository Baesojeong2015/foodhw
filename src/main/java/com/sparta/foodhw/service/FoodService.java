package com.sparta.foodhw.service;

import com.sparta.foodhw.dto.FoodRequestDto;
import com.sparta.foodhw.dto.RestaurantRequestDto;
import com.sparta.foodhw.model.Food;
import com.sparta.foodhw.model.Restaurant;
import com.sparta.foodhw.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FoodService {
    private final FoodRepository foodRepository; //FoodRepository를 서비스에게 주입하고있음.

    @Transactional
    public void registerFood(List<FoodRequestDto> requestDto, Restaurant restaurantId) {

        for (FoodRequestDto requestDto1 : requestDto) { //requestDto 안에 있는 값들을 requestDto1이라는 이름으로 가져오는데 가져오는 타입이 FoodRequestDto 타입.
            //requestDto1은 api명세서 requestBodySample 안에서 큰 배열 안에서 내용의 첫번째 중괄호 안 이름 쉑버거더블이랑 가격 10900원만 한 덩어리로 뽑아낸거임

            List<Food> foodList = foodRepository.findAllByRestaurant(restaurantId); //레스토랑 아이디에 해당하는 레스토랑의 푸드 정보를 갖고오는거
            for (Food foodCheck : foodList) { //ex)foodcheck: FoodList(전체 배열) 안의 name, price가 하나씩 들어가있는 중괄호 (쉑버거더블,10900)
                String name = requestDto1.getName(); //클라이언트에서 입력한 값 중 name을 받아온거임
                //foodcheck.getname()이 db안에 담겨있는 restaurantId에 해당하는 음식의 이름 (이미 들어간 값)
                //nameCheck는 db안에 이미 입력된 이름(foodcheck.getname())과 클라이언트에서 이제 받아온 값(name)을 비교(.equals)
                boolean nameCheck = foodCheck.getName().equals(name); //맞으면(똑같으면) 트루를 반환
                if (nameCheck == true) {
                    throw new IllegalArgumentException("중복된 음식 이름이 존재합니다");
                }
            }

            int price = requestDto1.getPrice(); //requestDto1.getPrice();는 RequdstBodySample에서 중괄호 하나 안의 price 하나.
            if (!(price >= 100 && price <= 1000000)) {
                throw new IllegalArgumentException("가격은 100원 ~ 1,000,000원 사이에서 가능합니다.");
            }
            if (price % 100 != 0) {
                throw new IllegalArgumentException("값은 100원 단위로만 입력 가능합니다.");
            }
            int a = 5;
            Food food = new Food(requestDto1, restaurantId); //new Food: 생성자 호출(딴 클래스에 있는 생성자)
            foodRepository.save(food); //레파지토리 안에 있는 내용을 서비스가 쓰겠다는뜻 .save는 db안에 저장하겠다는 뜻/ 저장하는 값이 food

        }
            //끝났는데 이셉션이 나지 않았다면 이 requestName은 중복된 이름이 아니기 때문에 사용할 수 있음.

    }

    @Transactional
    public List<Food> getFood(Restaurant restaurantId) {
        return foodRepository.findAllByRestaurant(restaurantId); //restaurantId에 해당하는 레스토랑 정보들을 가져오겠다.
    }


}
