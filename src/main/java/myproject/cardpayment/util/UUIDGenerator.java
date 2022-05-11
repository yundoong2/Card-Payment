package myproject.cardpayment.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UUIDGenerator {
    private final static int LENGTH_20_LONG_RADIX = 9;

    public static String createUUID() {
        UUID uuid = UUID.randomUUID();
        return parseToLongUUID(uuid.toString());
    }

    public static String parseToLongUUID(String uuid) {
        long l = ByteBuffer.wrap(uuid.getBytes()).getLong();
        return Long.toString(l, LENGTH_20_LONG_RADIX);
    }

}
