package com.example.DarkWebM.Model;

import lombok.*;

import java.util.Map;

@Setter
@Data
@Getter
public class LeakResult {
    private Map<String, Object> leakResults;

    public LeakResult() {
    }

    public LeakResult(Map<String, Object> leakResults) {
        this.leakResults = leakResults;
    }

}
