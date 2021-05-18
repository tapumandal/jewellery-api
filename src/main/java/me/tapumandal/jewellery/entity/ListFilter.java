package me.tapumandal.jewellery.entity;

import me.tapumandal.jewellery.entity.dto.ListFilterDto;
import org.springframework.stereotype.Component;

/**
 * Created by TapuMandal on 15/02/2021.
 * For any query ask online.tapu@gmail.com
 */

@Component
public class ListFilter {

    protected String categoryName;

    protected String sortBy;

    protected String sortType;

    public ListFilter() {
    }

    public ListFilter(ListFilterDto listFilterDto) {
        this.setCategoryName(listFilterDto.getCategoryName());
        this.setSortBy(listFilterDto.getSortBy());
        this.setSortType(listFilterDto.getSortType());
    }

    public String getCategoryName() {
        return categoryName == null ? "" : categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSortBy() {
        return sortBy == null ? "" : sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortType() {
        return sortType == null ? "" : sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
