package myproject.cardpayment.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommonResultDto implements Serializable {
    private static final long serialVersionUID = 5701023156103194719L;

    private String code;
    private String message;
    private Object data;

    public static CommonResultDto success(Object data){
        CommonResultDto dto = new CommonResultDto();
        dto.setCode("100");
        dto.setMessage("GOOD");
        dto.setData(data);
        return dto;
    }
}
