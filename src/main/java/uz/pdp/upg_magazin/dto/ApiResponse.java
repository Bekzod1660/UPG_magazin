package uz.pdp.upg_magazin.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is an api to get a
 * custom reference to the
 * frontend and is used instead of ResponseEntity
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    @JsonProperty("status_code")
    private int statusCode;
    private String message;
    private Object data;


}
