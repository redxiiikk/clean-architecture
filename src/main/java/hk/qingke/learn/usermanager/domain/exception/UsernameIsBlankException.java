package hk.qingke.learn.usermanager.domain.exception;

import hk.qingke.learn.usermanager.commons.AbstractCommonException;

public class UsernameIsBlankException extends AbstractCommonException {
    public UsernameIsBlankException() {
        super("user-name-blank");
    }
}
