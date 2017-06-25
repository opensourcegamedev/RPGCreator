package com.jukusoft.rpgcreator.engine.utils;

/**
 * Created by Justin on 29.06.2016.
 */
public class VPNUtils {

    protected static final String LOCAL_IP1 = "127.0.1.1";
    protected static final String LOCAL_IP2 = "127.0.0.1";

    /**
    * check, if ip is in vpn
     *
     * @param ip ip address of server
     *
     * @return true, if ip is in vpn
    */
    public static boolean isInVPN (String ip) {
        return ip.equals(LOCAL_IP1) || ip.equals(LOCAL_IP2) || ip.startsWith("10.");
    }

}
