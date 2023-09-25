package top.cbaymax.matrix.silk.dashboard.facade.model.base;

import jakarta.annotation.Nullable;

import java.util.List;

/**
 * @author 褚浩
 */

public class PageResult<T> extends Result<List<T>> {
    /**
     * 总记录数
     */
    public Long total;

    /**
     * 页码
     */
    public Integer pageNum;

    /**
     * 每页数量
     */
    public Integer pageSize;

    public PageResult(List<T> data) {
        super(data);
    }


    public void setPageInfo(Long total, Integer pageNum, Integer pageSize) {
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    /**
     * 获取总页数
     *
     * @return pageTotal
     */
    @Nullable
    public Integer getPageTotal() {
        if (this.total == null || this.pageSize == null) {
            return null;
        }
        return Double.valueOf(Math.ceil((total + 0.0) / pageSize)).intValue();
    }
}
