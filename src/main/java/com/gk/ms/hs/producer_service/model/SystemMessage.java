package com.gk.ms.hs.producer_service.model;

import lombok.Data;

import java.io.Serializable;

@Data

public class SystemMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String source;
    private String message;

}
