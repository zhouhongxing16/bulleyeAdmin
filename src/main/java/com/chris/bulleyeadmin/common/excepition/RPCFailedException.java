package com.chris.bulleyeadmin.common.excepition;

/**
 * Created by jack on 2017/4/18.
 */
public class RPCFailedException extends  RuntimeException{

    private static final long serialVersionUID = 151464436045015378L;
    public RPCFailedException(String msg) {
        super(msg);
    }
}
