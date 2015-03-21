package com.hpuvoice.ui.chat;

/**
 * Created by wei-spring on 2015/3/21.
 */
public interface OnOperationListener {

    public void send(String content);

    public void selectedFace(String content);

    public void selectedFuncation(int index);
}
