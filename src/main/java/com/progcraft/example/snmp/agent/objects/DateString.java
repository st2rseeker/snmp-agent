package com.progcraft.example.snmp.agent.objects;

import org.snmp4j.agent.MOAccess;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateString<V extends Variable> extends CustomManagedObject {

    public DateString(OID oid, MOAccess access) {
        super(oid, access, null);
    }

    @Override
    public V getValue() {

        String result = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        return (V) (new OctetString(result));
    }
}
