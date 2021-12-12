package com.strategy_pattern.context;

import com.strategy_pattern.strategy.Encryption;

public class Password {
    private String psd;
    private Encryption encryption;

    public void setPsd(String psd){
        this.psd = psd;
    }

    public void setEncryption(Encryption encryption){
        this.encryption = encryption;
    }

    public String getEncryption() throws Exception {
        return encryption.ciphertext(this.psd);
    }
}
