package com.xingtan.common.entity;

/**
 * 家庭成员的关系
 */
public enum FamilyRelation {
    /**
     * 母亲
     */
    MAMA,
    /**
     * 父亲
     */
    BABA,
    /**
     * 爷爷
     */
    YEYE,
    /**
     * 奶奶
     */
    NAINAI,
    /**
     * 姥姥
     */
    LAOLAO,
    /**
     * 姥爷
     */
    LAOYE,
    /**
     * 其他
     */
    OTHER;

//    public static FamilyRelation of(String name) {
//        for(FamilyRelation r: values()) {
//            if(r.name().equals(name)) {
//                return r;
//            }
//        }
//        return FamilyRelation.MAMA;
//    }


}
