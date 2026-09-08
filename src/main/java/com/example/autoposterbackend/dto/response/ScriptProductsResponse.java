package com.example.autoposterbackend.dto.response;

import com.example.autoposterbackend.dto.ScriptProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ScriptProductsResponse {
    private List<ScriptProductDto> products;
}
