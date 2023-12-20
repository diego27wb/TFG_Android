package com.example.prueba05;

import java.io.Serializable;

public enum SizeEnum implements Serializable {
    XS(1),
    S(2),
    M(5),
    L(10),
    XL(25),
    XXL(50);

    private final int value;

    SizeEnum(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}


