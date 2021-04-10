package hk.qingke.learn.usermanager.domain.exception;

import hk.qingke.learn.usermanager.commons.AbstractCommonException;

public class UsernameThanMaxLengthException extends AbstractCommonException {
    public UsernameThanMaxLengthException() {
        super("user-name-tan-max-length");
    }
}
