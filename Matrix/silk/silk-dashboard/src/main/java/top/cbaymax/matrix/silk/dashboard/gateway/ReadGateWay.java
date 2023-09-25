package top.cbaymax.matrix.silk.dashboard.gateway;

/**
 * @author 褚浩
 */
public interface ReadGateWay<DTO, Entity> {

    Entity toEntity(DTO dto);
}
