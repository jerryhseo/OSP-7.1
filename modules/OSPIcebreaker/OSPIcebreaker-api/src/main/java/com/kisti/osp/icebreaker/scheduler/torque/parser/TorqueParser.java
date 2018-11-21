package com.kisti.osp.icebreaker.scheduler.torque.parser;

public interface TorqueParser<X, T> {

    T parse(X x) throws Exception;

}
