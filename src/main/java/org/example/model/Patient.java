
package org.example.model;

import lombok.Data;

@Data
public class Patient {
    private long id;
    private String name;
    private long doctorId;
    private boolean discharged;
}

