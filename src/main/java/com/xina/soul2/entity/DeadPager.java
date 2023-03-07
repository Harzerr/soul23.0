package com.xina.soul2.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
@Setter
@Getter
@NoArgsConstructor
public class DeadPager implements Serializable {
    private List<Dead> deads;
    private int count;
}
