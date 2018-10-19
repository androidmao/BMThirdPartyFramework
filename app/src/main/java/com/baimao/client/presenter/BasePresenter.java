package com.baimao.client.presenter;

import com.baimao.client.net.NetworkManager;

public class BasePresenter {

    private NetworkManager manager;

    public NetworkManager getManager() {
        if (manager == null) {
            manager = new NetworkManager();
        }
        return manager;
    }
}
