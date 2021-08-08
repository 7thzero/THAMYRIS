package com.s7thzero.badjawa;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CodeFreqTable {
    private Map<Integer, Integer> mapHexDigitToFreq = null;

    class CodeAtom {
        public int freq;
        public int number;

        CodeAtom(int i, int i2) {
            this.freq = i;
            this.number = i2;
        }
    }

    public synchronized void Init() {
        if (this.mapHexDigitToFreq == null) {
            this.mapHexDigitToFreq = new HashMap();
            for (int i = 0; i < 16; i++) {
                this.mapHexDigitToFreq.put(Integer.valueOf(i), Integer.valueOf((AudioConst.space * i) + 3000));
            }
        }
    }

    public int getFreqFromNum(int i) {
        Init();
        Integer num = this.mapHexDigitToFreq.get(Integer.valueOf(i));
        if (num != null) {
            return num.intValue();
        }
        return -1;
    }

}