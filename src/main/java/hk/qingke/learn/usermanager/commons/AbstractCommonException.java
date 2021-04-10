package hk.qingke.learn.usermanager.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractCommonException extends RuntimeException {
    private String key;
}
