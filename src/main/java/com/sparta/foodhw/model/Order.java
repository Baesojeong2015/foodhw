package com.sparta.foodhw.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//SQL order ->순서를 나열하는 용어 'order by'

@Setter
@Getter
@Table(name = "orders")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //하나의 레스토랑에 대해 여러가지 주문이 있을 수 있어서 manytoone 사용
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "order")
    private List<OrderFood> orderFoods;


}
