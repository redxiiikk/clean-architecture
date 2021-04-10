package hk.qingke.learn.usermanager.domain.exception;

import hk.qingke.learn.usermanager.commons.AbstractCommonException;

public class PasswordIsBlankException extends AbstractCommonException {
    public PasswordIsBlankException() {
        super("user-password-blank");
    }
}
