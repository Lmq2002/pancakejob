package com.jbgz.pancakejob.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrderDTO {
    @JsonProperty("orderId")
    private int orderId;

    @JsonProperty("orderState")
    private int orderState;

    @JsonProperty("job")
    private OrderJobDTO job;
}
