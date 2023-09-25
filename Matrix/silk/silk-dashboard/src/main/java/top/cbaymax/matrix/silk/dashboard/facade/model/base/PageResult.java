package top.cbaymax.matrix.silk.dashboard.facade.model.base;

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

    public PageResult(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }


    public void setPageInfo(Long total, Integer pageNum, Integer pageSize) {
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public Integer getPageTotal() {
        if (this.total == null || this.pageSize == null) {
            return null;
        }
        return Double.valueOf(Math.ceil((total + 0.0) / pageSize)).intValue();
    }
}
