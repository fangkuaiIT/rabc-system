package com.lin.util.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * List Data
 *
 * @param <T> the type parameter
 * @author : fangkuaiIt / fangkuaiIt
 * @version : 1.0
 */
@ApiModel("分页数据")
public class ListData<T> extends BaseListData<T> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1525242899550063405L;

    /**
     * Page
     */
    protected PageImpl<T> page;

    /**
     * 处理后的data
     */
    @ApiModelProperty(name = "data", value = "data")
    private Object data;

    /**
     * List data.
     *
     * @param page the page
     */
    public ListData(PageImpl<T> page) {
        this.page = page;
    }

    @Override
    public int getNumber() {
        return page.getNumber() + 1;
    }

    @Override
    public int getSize() {
        return page.getSize();
    }

    @Override
    public int getTotalPages() {
        return page.getTotalPages();
    }

    @Override
    public long getTotalElements() {
        return page.getTotalElements();
    }

    @Override
    public boolean getHasPrevious() {
        return page.hasPrevious();
    }

    @Override
    public boolean getHasNext() {
        return page.hasNext();
    }

    @Override
    public boolean getIsFirst() {
        return page.isFirst();
    }

    @Override
    public boolean getIsLast() {
        return page.isLast();
    }

    @Override
    public List<T> getOriginData() {
        return page.getContent();
    }

    /**
     * 获取处理后的data
     *
     * @return the object
     * @author : fangkuaiIt / 2019-01-08
     */
    public Object getData() {
        return data;
    }

    /**
     * 处理data
     *
     * @param data the data
     * @author : fangkuaiIt
     * @Date : 2019-01-08
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 构建一个实例化ListData
     *
     * @param <E>  the type parameter
     * @param page the page
     * @return the list data
     * @author : fangkuaiIt / 2019-01-07
     */
    public static <E> ListData<E> of(Page<E> page){
        PageImpl<E> pageImpl = (PageImpl<E>) page;
        return new ListData<>(pageImpl);
    }

    /**
     * 构建一个实例化ListData.
     *
     * @param <E>   the type parameter
     * @param iPage the page
     * @return the list data
     * @author : fangkuaiIt / 2019-02-27
     */
    public static <E> ListData<E> of(IPage<E> iPage){
        List<E> content = iPage.getRecords();
        PageRequest pageRequest = PageRequest.of(Integer.valueOf(String.valueOf(iPage.getCurrent() - 1)), Integer.valueOf(String.valueOf(iPage.getSize())));
        PageImpl<E> pageImpl = new PageImpl<>(content, pageRequest, iPage.getTotal());
        return new ListData<>(pageImpl);
    }
}
