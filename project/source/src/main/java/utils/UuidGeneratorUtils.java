package utils;

import java.util.UUID;

public final class UuidGeneratorUtils {
    private UuidGeneratorUtils(){}
    public static String generateUUID(){
        return UUID.randomUUID().toString();
    }
}
