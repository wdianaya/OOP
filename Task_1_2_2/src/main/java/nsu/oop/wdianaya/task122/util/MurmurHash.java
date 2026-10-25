package nsu.oop.wdianaya.task122.util;

public final class MurmurHash {
    private static final int C1 = 0x85ebca6b;
    private static final int C2 = 0xc2b2ae35;
    private static final int SHIFT_16 = 16;
    private static final int SHIFT_13 = 13;

    public static int hash(Object key) {
        if (key == null) {
            return 0;
        }
        int h = key.hashCode();
        h ^= (h >>> SHIFT_16);
        h *= C1;
        h ^= (h >>> SHIFT_13);
        h *= C2;
        h ^= (h >>> SHIFT_16);
        return h;
    }

    private MurmurHash() {
    }
}
