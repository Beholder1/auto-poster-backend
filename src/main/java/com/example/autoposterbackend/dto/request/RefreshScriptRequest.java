package com.example.autoposterbackend.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class RefreshScriptRequest {
    private boolean refresh;
    private boolean allAccounts;
    private List<Integer> accountIds;
}
