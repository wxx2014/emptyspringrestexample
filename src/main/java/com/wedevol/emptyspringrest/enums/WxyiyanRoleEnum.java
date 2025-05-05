package com.wedevol.emptyspringrest.enums;

public enum WxyiyanRoleEnum {

    SYSTEM("system","系统"),
    TOOL("tool","工具"),
    USER("user","用户"),
    ASSISTANT("assistant","助手");

    private final String code;

    private final String desc;


    WxyiyanRoleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
