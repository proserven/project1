package com.revature.annotations;

public @interface NotEmpty {
    String message( ) default "Please enter a valid email";
}
