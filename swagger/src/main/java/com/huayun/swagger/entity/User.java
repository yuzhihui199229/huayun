package com.huayun.swagger.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class User {
    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "用户地址")
    private String address;
    //getter/setter
}