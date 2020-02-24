package com.lin.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 条件查询参数对象
 *
 * @author : fangkauiIT
 * @version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchParam implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5094096637006312823L;


    /**
     * 查询的字段名
     */
    private String fieldName;

    /**
     * 查询方式
     */
    private Operator operator;

    /**
     * 值1
     */
    private String value;

    /**
     * 值2(日期查询需要用到值1和值2)
     */
    private String value1;


    /**
     * 查询方式枚举
     *
     * @author : fangkauiIT
     * @version : 1.0
     */
    public enum Operator {
        /**
         * 等于.
         */
        eq,

        /**
         * 不等于.
         */
        ne,

        /**
         * 大于.
         */
        gt,

        /**
         * 大于等于.
         */
        ge,

        /**
         * 小于.
         */
        lt,

        /**
         * 小于等于.
         */
        le,

        /**
         * 区间.
         */
        between,

        /**
         * 区间.
         */
        notBetween,

        /**
         * 模糊查询(值左右两边模糊匹配查询).
         */
        like,

        /**
         * 模糊查询取不符合条件.
         */
        notLike,

        /**
         * 模糊查询(值左边模糊匹配查询).
         */
        likeLeft,

        /**
         * 模糊查询(值右边模糊匹配查询).
         */
        likeRight,

        /**
         * 指定范围查询(值以','隔开,；例如 id1,id2,id3...).
         */
        in,

        /**
         * 指定范围查询(值以','隔开,；例如 id1,id2,id3...)取不符合条件
         * .
         */
        notIn
    }
}
