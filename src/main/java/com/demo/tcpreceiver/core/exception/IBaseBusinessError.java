package com.demo.tcpreceiver.core.exception;

import java.util.Locale;

public interface IBaseBusinessError {
    int getStatus();

    String getMessage();

    String getCode();

    String getMessage(Locale var1);
}
