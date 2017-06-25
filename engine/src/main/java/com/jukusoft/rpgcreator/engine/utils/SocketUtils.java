/*
* Coypright (c) 2015 Justin Kuenzel
* Apache 2.0 License
*
* This file doesnt belongs to the Pentaquin Project.
* This class is owned by Justin Kuenzel and licensed under the Apache 2.0 license.
* Many projects use this class.
*/

package com.jukusoft.rpgcreator.engine.utils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Utils class for socket operations
 *
 * Created by Justin on 03.09.2015.
 */
public class SocketUtils {

    /**
    * check, if the remote port is open
     *
     * @param hostname hostname or ip address
     * @param port number of port to check
     * @param timeout timeout in seconds
    */
    public static boolean checkIfRemotePortAvailable (String hostname, int port, int timeout) {
        try {
            Socket s = new Socket();
            s.setReuseAddress(true);
            SocketAddress sa = new InetSocketAddress(hostname, port);

            try {
                s.connect(sa, timeout * 1000);
            } catch (IOException e) {
                s.close();
                return false;
            }

            if (s.isConnected()) {
                s.close();

                return true;
            } else {
                s.close();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * check, if the remote udp port is open
     *
     * @param hostname hostname or ip address
     * @param port number of port to check
     * @param timeout timeout in seconds
     *
     * @return true, if port is open
     */
    public static boolean checkIfUDPPortIsOpen (String hostname, int port, int timeout) {
        try{
            //create inet address
            InetAddress address = InetAddress.getByName(hostname);

            byte [] bytes = new byte[128];
            DatagramSocket ds = new DatagramSocket();
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
            ds.setSoTimeout(timeout);
            ds.connect(address, port);
            ds.send(dp);
            ds.isConnected();
            dp = new DatagramPacket(bytes, bytes.length);
            ds.receive(dp);
            ds.close();

            return true;
        } catch(InterruptedIOException e){
            //System.out.println("closed");
            return false;
        } catch(IOException e){
            //System.out.println("closed");
            return false;
        }
    }

    /**
    * list own ip addresses
     *
     * @return list with own ip addresses
    */
    public static List<String> listOwnIPs () throws UnknownHostException, SocketException {
        //create new empty list with ip addresses
        List<String> ownIPList = new ArrayList<>();

        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

        //iterate through network interfaves
        for (; networkInterfaces.hasMoreElements();) {
            //get network interface
            NetworkInterface networkInterface = networkInterfaces.nextElement();

            //get ip addresses from network interface
            Enumeration<InetAddress> inetAddressEnumeration = networkInterface.getInetAddresses();

            //iterate through ip address enumeration
            for (; inetAddressEnumeration.hasMoreElements();) {
                //get ip address
                InetAddress inetAddress = inetAddressEnumeration.nextElement();

                //add ip address to list
                ownIPList.add(inetAddress.getHostAddress());
            }
        }

        //return list with ip addresses
        return ownIPList;
    }

}
