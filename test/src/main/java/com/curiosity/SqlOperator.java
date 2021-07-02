package com.curiosity;

/**
 * @Classname Operator
 * @Description SQL运算符
 * @Date 2021/5/14 14:01
 * @Created by lijinze
 */
public enum SqlOperator {
    /**
     * 等于
     */
    eq("="),

    /**
     * 不等于
     */
    ne("!="),

    /**
     * 大于
     */
    gt(">"),

    /**
     * 小于
     */
    lt("<"),

    /**
     * 大于等于
     */
    ge(">="),

    /**
     * 小于等于
     */
    le("<="),

    /**
     * 类似
     */
    like("like"),
    /**
     * 类似
     */
    likeLeft("likeLeft"),
    /**
     * 类似
     */
    likeRight("likeRight"),
    /**
     * 类似
     */
    notLike("notLike"),
    /**
     * 类似
     */
    between("between"),

    /**
     * 包含
     */
    in("in"),

    /**
     * 包含
     */
    notIn("not in"),

    /**
     * 为Null
     */
    isNull("is Null"),

    /**
     * 不为Null
     */
    isNotNull("is Not Null"),
    /**
     * 不为OR
     */
    or("OR");
    private String operator;

    SqlOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
