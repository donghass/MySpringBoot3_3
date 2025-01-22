package com.basic.myspringboot.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer {
@Id //pk
@GeneratedValue(strategy = GenerationType.IDENTITY) //pk의 sequential 값을 자동 증가
private Long id;

@Column(unique=true, nullable = false, name = "cust_id")
private String customerId;

@Column(nullable = false, name = "cust_name")
private String customerName;
}