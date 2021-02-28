package com.ditsov.basicschoolgradingsystem.model.basic;

import java.io.Serializable;

import lombok.Data;

@Data
public class BasicDTO implements Serializable {
    
    	private static final long serialVersionUID = 4631419914873243413L;
    
    	private Long id;
    	private String name;

}
