package com.lin.search;



import com.lin.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * 分页数据
 *
 * @author :fangkauiIT
 * @version : 1.0
 */
public class JpaListData extends BaseListData {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1765224769198142807L;


    /**
     * Page
     */
    protected PageImpl page;

    /**
     * Jpa list data.
     *
     * @param page the page
     */
    public JpaListData(PageImpl page) {
        this.page = page;
    }

    @Override
    public long getPage() {
        return page.getNumber() + 1;
    }

    @Override
    public long getSize() {
        return page.getSize();
    }

    @Override
    public long getTotalPages() {
        return page.getTotalPages();
    }

    @Override
    public long getTotalElements() {
        return page.getTotalElements();
    }

    @Override
    public Boolean getHasPrevious() {
        return page.hasPrevious();
    }

    @Override
    public Boolean getHasNext() {
        return page.hasPrevious();
    }

    @Override
    public Boolean isFirst() {
        return page.isFirst();
    }

    @Override
    public Boolean isLast() {
        return page.isLast();
    }

    /**
     * Of jpa list data.
     *
     * @param page the page
     * @return the jpa list data
     * @author : fangkauiIT / 2019-04-15
     */
    public static <T extends BaseEntity> JpaListData of(Page<T> page){
        PageImpl<T> pageImpl = (PageImpl<T>) page;
        JpaListData listData = new JpaListData(pageImpl);
        listData.setData(pageImpl.getContent());
        return listData;
    }
}
