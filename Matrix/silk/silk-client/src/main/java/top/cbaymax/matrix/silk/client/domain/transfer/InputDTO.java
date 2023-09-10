package top.cbaymax.matrix.silk.client.domain.transfer;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@Builder
public class InputDTO {

    private Map<String, Object> extend;

    private Date mockTime;
}
