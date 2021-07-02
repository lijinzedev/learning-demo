package com.curiosity;

/**
 * @Classname Constants
 * @Description SQL 权限常量
 * @Date 2021/5/14 13:36
 * @Created by lijinze
 */
public interface SqlRoleConstants {
    /**
     * 用户ID
     */
    String OPERATOR_ID = "OPERATOR_ID";
    /**
     * 组织ID
     */
    String ORGANIZE_NO = "ORGANIZE_NO";
    String ANCESTORS = "ancestors";

    /**
     * 角色ID
     */
    String ROLE_ID="ROLE_ID";

    /**
     * 组织表
     */
    String ORGANIZE_TABLE = "c_organize";
    /**
     * 角色表
     */
    String ROLE_TABLE = "c_role";

    /**
     * 角色部门规则表
     */
    String ROLE_ORGANIZE_TABLE ="c_role_organize";
}
