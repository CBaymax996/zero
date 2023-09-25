package top.cbaymax.matrix.silk.dashboard.facade.model.base;

/**
 * 排序字段
 *
 * @author 褚浩
 */
public class SortField {

    String filedName;

    SortOrder sortOrder;

    public enum SortOrder {
        DESC,
        ASC
    }
}
