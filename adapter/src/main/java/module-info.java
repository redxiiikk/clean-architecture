module hk.qingke.learn.usermanager.adapter {
    requires static lombok;
    requires static org.mapstruct;
    requires static java.compiler;

    requires spring.web;
    requires hk.qingke.learn.usermanager.service;
    requires hk.qingke.learn.usermanager.domain;
}