package com.progcraft.example.snmp.agent;

import com.progcraft.example.snmp.agent.objects.CustomManagedObject;
import org.snmp4j.agent.MOAccess;
import org.snmp4j.agent.mo.MOAccessImpl;
import org.snmp4j.agent.mo.MOScalar;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;

public class ManagedObjectFactory {

    public static MOScalar createReadOnly(String oid, Object value) {
        return getManagedObject(new OID(oid), MOAccessImpl.ACCESS_READ_ONLY, value);
    }

    public static MOScalar createReadWrite(String oid, Object value) {
        return getManagedObject(new OID(oid), MOAccessImpl.ACCESS_READ_WRITE, value);
    }

    public static MOScalar createWriteOnly(String oid, Object value) {
        return getManagedObject(new OID(oid), MOAccessImpl.ACCESS_WRITE_ONLY, value);
    }

    private static MOScalar getManagedObject(OID oid, MOAccess access, Object value) {

        MOScalar mo;

        if (value == null) {
            mo = null;
        } else if (value instanceof String) {
            mo = getCustomManagedObject(oid, access, (String) value);
            if (mo == null) {
                mo = new MOScalar(oid, access, getVariable(value));
            }
        } else {
            mo = new MOScalar(oid, access, getVariable(value));
        }

        return mo;
    }

    private static MOScalar getCustomManagedObject(OID oid, MOAccess access, String objectName) {

        CustomManagedObject mo = null;

        try {

            mo = (CustomManagedObject) Class.forName("com.progcraft.example.snmp.objects." + capitalize(objectName))
                    .getConstructor(OID.class, MOAccess.class)
                    .newInstance(oid, access);

        } catch (Exception e) {
            // nothing to do
        }

        return mo;
    }

    private static Variable getVariable(Object value) {

        if (value instanceof String || value instanceof Float || value instanceof Double) {
            return new OctetString(value.toString());
        } else if (value instanceof Integer) {
            return new Integer32((Integer) value);
        }
        // TODO handle additional data types, if required

        throw new IllegalArgumentException("Unmanaged Type: " + value.getClass());
    }

    private static String capitalize(String str) {
        int strLen;
        return str != null && (strLen = str.length()) != 0 ? (new StringBuilder(strLen)).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString() : str;
    }
}
