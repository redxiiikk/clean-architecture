package hk.qingke.learn.usermanager.service.exception;

import hk.qingke.learn.usermanager.commons.AbstractCommonException;

public class UserEmailDuplicationException extends AbstractCommonException {
    public UserEmailDuplicationException() {
        super("user-email-duplication");
    }
}
