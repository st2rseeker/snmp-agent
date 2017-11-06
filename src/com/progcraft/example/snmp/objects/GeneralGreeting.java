package com.progcraft.example.snmp.objects;

import org.snmp4j.agent.MOAccess;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

public class GeneralGreeting<V extends Variable> extends CustomManagedObject {

    public GeneralGreeting(OID oid, MOAccess access) {
        super(oid, access, null);
    }

    @Override
    public V getValue() {

        String result = "Hello SNMP World!";

        return (V) (new OctetString(result));
    }
}
