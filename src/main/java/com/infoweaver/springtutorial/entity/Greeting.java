package com.infoweaver.springtutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Ruobing Shang 2022-08-31
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Greeting {
    private long id;
    private String content;

}
