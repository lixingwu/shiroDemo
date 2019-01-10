package com.example.shirodemo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private Long id;

    private String writerName;

    private Boolean isDelete;

    private String userName;

    private String userPhone;

    private String userPwd;

    private String userType;

    private Long companyId;

    private String userRemarks;


}