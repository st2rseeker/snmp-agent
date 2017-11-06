package com.progcraft.example.snmp.agent.objects;

import org.snmp4j.agent.MOAccess;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;

public class CustomManagedObject<V extends Variable> extends MOScalar {

    protected CustomManagedObject(OID oid, MOAccess access, V value) {
        super(oid, access, value);
    }

    @Override
    public V getValue() {
        return (V) super.getValue();
    }
}
