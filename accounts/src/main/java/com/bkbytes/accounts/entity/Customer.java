package com.bkbytes.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor@NoArgsConstructor
public class Customer extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="customer_id", updatable = false)
  private Long customerId;

  @Column(name="name")
  private String name;

  @Column(name="email")
  private String email;

  @Column(name="mobileNumber")
  private String mobileNumber;
}
