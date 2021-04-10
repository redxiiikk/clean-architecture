package hk.qingke.learn.usermanager.domain.exception;

import hk.qingke.learn.usermanager.commons.AbstractCommonException;

public class PasswordIllegalException extends AbstractCommonException {
    public PasswordIllegalException() {
        super("user-password-illegal");
    }
}
