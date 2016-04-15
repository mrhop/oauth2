package com.hopever.hope.oauth2.dm.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by Donghui Huo on 2016/3/22.
 */
public class UserNotActivatedException extends AuthenticationException {

    public UserNotActivatedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotActivatedException(String msg) {
        super(msg);
    }

}
