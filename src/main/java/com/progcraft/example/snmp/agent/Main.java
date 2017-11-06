package com.progcraft.example.snmp.agent;

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

            // just keep running the process
            // in a regular scenario the agent will be instantiated in a living process
            while(true) {
                Thread.sleep(10000);
            }

        } catch (Exception e) {
            System.out.println("Failed to start SNMP agent on port 12345 : " + e.getMessage());
        }
    }
}
