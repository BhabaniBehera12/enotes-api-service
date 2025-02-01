package com.becoder.Exception;

import com.becoder.CatagoryDto.CategoryDto;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Validation {

    public void categoryValiditaion(CategoryDto categoryDto) {
        Map<String,Object>error= new LinkedHashMap<>();
        if (ObjectUtils.isEmpty(categoryDto)){
            throw new IllegalArgumentException("category object/JSON shouidn't be null or emty");
        }else {

            //validation nameField
            if (ObjectUtils.isEmpty(categoryDto.getName()))
            {
                error.put("name","name field is empty or null");
            }else {
                if (categoryDto.getDescription().length()<10){
                    error.put("name","name length min 10");
                }
                if (categoryDto.getName().length()>100){
                    error.put("name","name length max 10");
                }
            }
            //validation Description
            if (ObjectUtils.isEmpty(categoryDto.getDescription()))
            {
                error.put("Description","Description field is empty or null");
//
            }
            //validation IsActive
            if (ObjectUtils.isEmpty(categoryDto.getIsActive()))
            {
                error.put("IsActive","IsActive field is empty or null");
            }else {
                if (categoryDto.getIsActive() !=Boolean.TRUE.booleanValue() && categoryDto.getIsActive()!=Boolean.FALSE .booleanValue()){
                    error.put("IsActive"," invalid value IsActive field");

                }
            }

        }

        if ((!error.isEmpty())){
            throw  new ValidationException(error);
        }
    }


}
