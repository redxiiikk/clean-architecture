open module hk.qingke.learn.usermanager.infrastructure {
    requires static lombok;
    requires static org.mapstruct;
    requires static java.compiler;

    requires org.mybatis;
    requires hk.qingke.learn.usermanager.service;
    requires hk.qingke.learn.usermanager.domain;

    exports hk.qingke.learn.usermanager.infrastructure.repository;
}