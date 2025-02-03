package com.becoder.Handler;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class GenericResponce {

    private HttpStatus ResponceStatus;
    private String status;//sucess,faied
    private String message;//save sucessfully
    private  Object data;//data


    public ResponseEntity<?> create() {
        Map<String, Object> map = new LinkedHashMap<>();

map.put("status",status);
map.put("message",message);
    if (!ObjectUtils.isEmpty(data)){
        map.put("data",data);
    }
    return  new ResponseEntity<>(map,ResponceStatus);
    }
}
