package com.beaverg.domain.dummy_json.users;

import java.util.Objects;

public class Crypto {
    private String coin;
    private String wallet;
    private String network;

    public Crypto() { }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public String getWallet() {
        return wallet;
    }

    public void setWallet(String wallet) {
        this.wallet = wallet;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crypto crypto = (Crypto) o;
        return Objects.equals(coin, crypto.coin) &&
                Objects.equals(wallet, crypto.wallet) &&
                Objects.equals(network, crypto.network);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coin, wallet, network);
    }
}
