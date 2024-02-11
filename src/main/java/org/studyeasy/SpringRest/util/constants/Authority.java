package org.studyeasy.SpringRest.util.constants;

public enum Authority {
    READ,
    WRITE,
    UPDATE,
    USER, //can update delete self object and read anything
    ADMIN // can read update delete any object 
}
