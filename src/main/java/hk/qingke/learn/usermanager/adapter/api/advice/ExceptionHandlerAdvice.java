package hk.qingke.learn.usermanager.adapter.api.advice;

import hk.qingke.learn.usermanager.commons.AbstractCommonException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    public static final String DEFAULT_EXCEPTION_MESSAGE_I18N_KEY = "common-exception-message";
    private final MessageSource i18nMessage;

    public ExceptionHandlerAdvice(MessageSource i18nMessage) {
        this.i18nMessage = i18nMessage;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AbstractCommonException.class)
    public String commonExceptionHandle(HttpServletRequest request, AbstractCommonException exception) {

        log.error(exception.getKey(), exception);

        String acceptLanguage = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);

        if (StringUtils.isBlank(acceptLanguage)) {
            return this.i18nMessage.getMessage(DEFAULT_EXCEPTION_MESSAGE_I18N_KEY, null, Locale.SIMPLIFIED_CHINESE);
        }

        String exceptionMessage = this.i18nMessage.getMessage(exception.getKey(), null, Locale.forLanguageTag(acceptLanguage));

        if (StringUtils.isBlank(exceptionMessage)) {
            return this.i18nMessage.getMessage(DEFAULT_EXCEPTION_MESSAGE_I18N_KEY, null, Locale.SIMPLIFIED_CHINESE);
        }

        return exceptionMessage;
    }
}
