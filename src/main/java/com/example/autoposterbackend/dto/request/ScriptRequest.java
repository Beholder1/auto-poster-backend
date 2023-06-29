package com.example.autoposterbackend.dto.request;

import com.example.autoposterbackend.dto.ProductWithImageDto;
import lombok.Getter;

import java.util.List;

@Getter
public class ScriptRequest {
    private List<Integer> accountIds;
    private List<ProductWithImageDto> productsWithImages;
    private Boolean hideBeforeFriends;
}
