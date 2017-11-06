package com.progcraft.example.snmp;

public class Main {

    public static void main(String[] args) {

        try {

            // create an agent to listen at localhost:12345
            SNMPAgent snmpAgent = new SNMPAgent("0.0.0.0/12345");

            // actually start listening
            snmpAgent.start();

            // register the custom mib information
            snmpAgent.registerCustomMIB();

            System.out.println("SNMP agent listening on port 12345");

        } catch (Exception e) {

            System.out.println("Failed to start SNMP agent on port 12345 : " + e.getMessage());
        }
    }
}
