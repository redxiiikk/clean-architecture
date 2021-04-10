open module hk.qingke.learn.usermanager.service {
    requires hk.qingke.learn.usermanager.entity;

    requires org.apache.commons.lang3;

    exports hk.qingke.learn.usermanager.service;
    exports hk.qingke.learn.usermanager.service.exception;
    exports hk.qingke.learn.usermanager.service.gateway.repository;
}