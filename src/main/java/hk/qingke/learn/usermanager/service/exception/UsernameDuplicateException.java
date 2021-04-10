package hk.qingke.learn.usermanager.service.exception;

import hk.qingke.learn.usermanager.commons.AbstractCommonException;

public class UsernameDuplicateException extends AbstractCommonException {
    public UsernameDuplicateException() {
        super("user-name-duplication");
    }
}
