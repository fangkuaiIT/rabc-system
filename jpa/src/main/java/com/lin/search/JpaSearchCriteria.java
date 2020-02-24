package com.lin.search;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.lin.constant.Constants;
import com.lin.utils.LocalDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 查询对象
 *
 * @author : fangkauiIT /
 * @version : 1.0
 */
public class JpaSearchCriteria extends BaseSearchCriteria {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2788620034795290884L;

    /**
     * 范围分隔符
     */
    private final static String IN_SPLIT = ",";


    /**
     * 构建分页.
     *
     * @return the page request
     * @author : fangkauiIT / 2019-04-16
     */
    public PageRequest buildPage(){
        Sort sort = buildSort();
        //PageRequest的起始页由0开始
        return PageRequest.of(Integer.valueOf(String.valueOf(getPage() - 1)), Integer.valueOf(String.valueOf(getSize())), sort);
    }

    /**
     * 构建排序.
     *
     * @return the sort
     * @author : fangkauiIT / 2019-04-16
     */
    public Sort buildSort(){
        Sort sort;

        if (!Strings.isNullOrEmpty(getField()) && null != getOrder()){
            sort = new Sort(Sort.Direction.fromString(getOrder().toString()), getField());
        }
        else {
            sort = new Sort(Sort.Direction.DESC, "updateDate");
        }

        return sort;
    }


    /**
     * 构建查询条件.
     *
     * @param <T> the type parameter
     * @return the specification
     * @author : fangkauiIT / 2019-04-16
     */
    public <T> Specification<T> buildQuery(){
        return ((root, query, cb) -> {
            Set<SearchParam> searchParams = getSearchParams();
            List<Predicate> predicates = Lists.newArrayList();

            //过滤逻辑删除数据
            Path deleteTag = root.get("deleteTag");
            predicates.add(cb.equal(deleteTag, Constants.UNDELETE_TAG));

            //条件查询
            if (null != searchParams && searchParams.size() > 0){

                searchParams.forEach(search -> {
                    //遍历条件对象
                    if(!Strings.isNullOrEmpty(search.getFieldName())){
                        String[] names = StringUtils.split(search.getFieldName(), ",");
                        Path expression = root.get(names[0]);
                        for (int i = 1; i < names.length; i++){
                            expression = expression.get(names[i]);
                        }

                        switch (search.getOperator()){
                            //等于
                            case eq:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.equal(expression, search.getValue()));
                                }
                                break;

                            //不等于
                            case ne:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.notEqual(expression, search.getValue()));
                                }
                                break;

                            //大于
                            case gt:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.greaterThan(expression, search.getValue()));
                                }
                                break;

                            //大于等于
                            case ge:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.greaterThanOrEqualTo(expression, search.getValue()));
                                }
                                break;

                            //小于
                            case lt:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.lessThan(expression, search.getValue()));
                                }
                                break;

                            //小于等于
                            case le:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.lessThanOrEqualTo(expression, search.getValue()));
                                }
                                break;

                            //区间(仅日期查询使用)
                            case between:
                                //startDate
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.greaterThanOrEqualTo(expression, Date.from(LocalDateUtils.getInstant(search.getValue()))));
                                }
                                //endDate
                                if (!Strings.isNullOrEmpty(search.getValue1())){
                                    predicates.add(cb.lessThanOrEqualTo(expression, Date.from(LocalDateUtils.getInstant(search.getValue1()))));
                                }
                                break;

                            //区间(仅日期查询使用)
                            case notBetween:
                                //startDate
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.lessThanOrEqualTo(expression, Date.from(LocalDateUtils.getInstant(search.getValue()))));
                                }
                                //endDate
                                if (!Strings.isNullOrEmpty(search.getValue1())){
                                    predicates.add(cb.greaterThanOrEqualTo(expression, Date.from(LocalDateUtils.getInstant(search.getValue1()))));
                                }
                                break;

                            //模糊查询(值左右两边模糊匹配查询)
                            case like:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.like(expression, "%" + search.getValue() + "%"));
                                }
                                break;

                            //模糊查询取不符合条件
                            case notLike:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.notLike(expression, "%" + search.getValue() + "%"));
                                }
                                break;

                            //模糊查询(值左边模糊匹配查询)
                            case likeLeft:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.like(expression, "%" + search.getValue()));
                                }
                                break;

                            //模糊查询(值右边模糊匹配查询)
                            case likeRight:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(cb.like(expression, search.getValue() + "%"));
                                }
                                break;

                            //指定范围查询(值以','隔开,；例如 id1,id2,id3...)
                            case in:
                                if (!Strings.isNullOrEmpty(search.getValue())){
                                    predicates.add(expression.in(Lists.newArrayList(StringUtils.split(search.getValue(), IN_SPLIT))));
                                }
                                break;

                            default:
                                break;
                        }
                    }
                });
            }

            //and条件
            if (predicates.size() > 0){
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
            else {
                return cb.conjunction();
            }
        });
    }
}
