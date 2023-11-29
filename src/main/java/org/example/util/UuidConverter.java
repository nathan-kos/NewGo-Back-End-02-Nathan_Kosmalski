package org.example.util;

import java.util.UUID;

public abstract class UuidConverter {

    public static UUID toUuid(String uuid) {
        return UUID.fromString(uuid);
    }

    public static UUID getUuid(String uuid) {
        return UUID.randomUUID();
    }

    public static boolean isValid(String uuid) {
        try {
            UUID.fromString(uuid);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
