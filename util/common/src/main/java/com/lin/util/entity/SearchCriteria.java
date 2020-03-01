package com.lin.util.entity;


import com.lin.util.utils.ToolUtils;
import com.yyfly.common.search.GlobalSpecification;
import com.yyfly.common.util.DateUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询类
 *
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@Data
@ApiModel("分页查询")
public class SearchCriteria{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3306754931093890824L;

    /**
     * 开始行号
     */
    @ApiModelProperty(name = "number", value = "开始行号")
    private int number = 1;
    /**
     * 每页多少条记录
     */
    @ApiModelProperty(name = "size", value = "每页多少条记录")
    private int size = 10;

    /**
     * 排序字段
     */
    @ApiModelProperty(name = "field", value = "排序字段")
    private String field;
    /**
     * 排序规则
     */
    @ApiModelProperty(name = "direction", value = "排序规则(desc降序, asc升序)")
    private String direction;

    /**
     * 快速查询值
     */
    @ApiModelProperty(name = "fastSearch", value = "快速查询值")
    private String fastSearch;

    /**
     * 快速查询集合
     */
    @ApiModelProperty(name = "fastSearchParams", value = "快速查询集合", hidden = true)
    private List<FastSearchParam> fastSearchParams;

    /**
     * 参数查询集合
     */
    private List<SearchParam> searchParams;


    /**
     * Build search params specification.
     *
     * @param <T> the type parameter
     * @return the specification
     * @author : fangkuaiIt / 2019-01-18
     */
    public <T> Specification<T> buildSearchParams(){
        return new Specification<T>() {
//            Root<T>root：代表了可以查询和操作的实体对象的根。如果将实体对象比喻成表名，那root里面就是这张表里面的字段。
//            这不过是JPQL的实体字段而已。通过里面的Path<Y>get(StringattributeName)来获得我们操作的字段。
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //and连接
                List<Predicate> predicates = null;
                //or连接
                List<Predicate> fastPredicates = null;

                //参数查询语句
                if (searchParams != null && searchParams.size() > 0){
                    predicates = new ArrayList<>(searchParams.size());
                    for (SearchParam search : searchParams){

                        if (StringUtils.isEmpty(search.getFieldName())){
                            continue;
                        }

                        String[] names = StringUtils.split(search.getFieldName(), ",");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++){
                            expression = expression.get(names[i]);
                        }
                        if (!GlobalSpecification.isNullOrEmpty(search.getFieldName())){
                            switch (search.getOperator()){
                                case "eq": //等于
                                    if (!org.springframework.util.StringUtils.isEmpty(search.getValue())){
                                        predicates.add(cb.equal(expression, search.getValue()));
                                    }
                                    break;
                                case "ne": //不等于
                                    if (!org.springframework.util.StringUtils.isEmpty(search.getValue())){
                                        predicates.add(cb.notEqual(expression, search.getValue()));
                                    }
                                    break;
                                case "like": //模糊查询
                                    if (!org.springframework.util.StringUtils.isEmpty(search.getValue())){
                                        predicates.add(cb.like(expression, "%" + search.getValue() + "%"));
                                    }
                                    break;
                                case "between": //区间(日期查询专用)
                                    String value = (String) search.getValue();
                                    String value1 = (String) search.getValue1();
                                    if (StringUtils.isNotEmpty(value)){
                                        predicates.add(cb.greaterThanOrEqualTo(expression, DateUtils.string2Date(value, DateUtils.DEFAULT_PATTERN)));
                                    }
                                    if (StringUtils.isNotEmpty(value1)){
                                        predicates.add(cb.lessThanOrEqualTo(expression, DateUtils.string2Date(value1, DateUtils.DEFAULT_PATTERN)));
                                    }
                                    break;
                                case "ge": //大于等于
                                    if (!org.springframework.util.StringUtils.isEmpty(search.getValue())){
                                        predicates.add(cb.greaterThanOrEqualTo(expression, (Comparable) search.getValue()));
                                    }
                                    break;
                                case "le": //小于等于
                                    if (!org.springframework.util.StringUtils.isEmpty(search.getValue())){
                                        predicates.add(cb.lessThanOrEqualTo(expression, (Comparable) search.getValue()));
                                    }
                                    break;
                                case "gt": //大于
                                    if (!org.springframework.util.StringUtils.isEmpty(search.getValue())){
                                        predicates.add(cb.greaterThan(expression, (Comparable)search.getValue()));
                                    }
                                    break;
                                case "lt": //小于
                                    if (!org.springframework.util.StringUtils.isEmpty(search.getValue())){
                                        predicates.add(cb.lessThan(expression, (Comparable) search.getValue()));
                                    }
                                    break;
                                case "in": //范围(值为以','连接的字符串， 例如 id1,id2...)
                                    List<String> ids = ToolUtils.stringToList((String) search.getValue());
                                    if (ids == null || ids.size() == 0){
                                        predicates.add(cb.equal(expression, ""));
                                    }else {
                                        predicates.add(expression.in(ids));
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }

                //快速查询条件语句
                if (StringUtils.isNotEmpty(fastSearch)){
                    fastPredicates = new ArrayList<>(fastSearchParams.size());
                    for (FastSearchParam search : fastSearchParams){

                        String[] names = StringUtils.split(search.getFieldName(), ",");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++){
                            expression = expression.get(names[i]);
                        }

                        if (search.isSubQuery()){
                            List<String> ids = search.getIds();
                            if (ids != null && ids.size() > 0){
                                fastPredicates.add(expression.in(search.getIds()));
                            }
                            else {
                                fastPredicates.add(expression.in(""));
                            }
                        }
                        else {
                            fastPredicates.add(cb.like(expression, "%" + fastSearch + "%"));
                        }
                    }
                }

                //连接所有条件
                if (predicates != null && predicates.size() > 0){
                    Predicate predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));

                    if (fastPredicates != null && fastPredicates.size() > 0){
                        Predicate fastPredicate = cb.or(fastPredicates.toArray(new Predicate[fastPredicates.size()]));
                        return cb.and(predicate, fastPredicate);
                    }else {

                        return predicate;
                    }
                }
                else {
                    if (fastPredicates != null && fastPredicates.size() > 0){
                        return cb.or(fastPredicates.toArray(new Predicate[fastPredicates.size()]));
                    }
                }

                return cb.conjunction();
            }
        };
    }

    /**
     * 创建分页请求.
     *
     * @return page request
     * @author : yyfly / 2018-09-12
     */
    public PageRequest buildPageRequest() {
        Sort sort = buildSort();
        //PageRequest的起始页由0开始
        return PageRequest.of(number - 1, size, sort);
    }


    /**
     *  创建排序.
     *
     * @return the sort
     * @author : fangkuaiIt / 2019-03-09
     */
    public Sort buildSort(){
        Sort sort;
        if (StringUtils.isNoneEmpty(field) && StringUtils.isNoneEmpty(direction)) {
            sort = Sort.by(Sort.Direction.fromString(direction), field);
        } else {
            //如果没传则默认使用 createDate 作为排序字段
            sort =  Sort.by(Sort.Direction.DESC, "updateDate");
        }

        return sort;
    }
}
