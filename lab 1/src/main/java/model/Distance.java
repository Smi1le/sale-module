package model;

import lombok.Data;

@Data
public class Distance {

    private Integer min;

    private Integer max;

    public Distance(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

}
