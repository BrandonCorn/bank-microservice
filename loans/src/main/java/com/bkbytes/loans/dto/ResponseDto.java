package com.bkbytes.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto {

  private String responseCode;

  private String message;
}
