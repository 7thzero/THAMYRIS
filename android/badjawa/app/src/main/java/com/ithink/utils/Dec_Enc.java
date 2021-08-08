package com.ithink.utils;

import java.lang.reflect.Array;
import u.aly.TTypeStaticByteValueAllocationCl;
public class Dec_Enc {
    static final int[] _iip_tab_p = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25};
    static final int[][][] ccom_SSS_p = {new int[][]{new int[]{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7}, new int[]{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8}, new int[]{4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0}, new int[]{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}}, new int[][]{new int[]{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10}, new int[]{3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5}, new int[]{0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15}, new int[]{13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}}, new int[][]{new int[]{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8}, new int[]{13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1}, new int[]{13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7}, new int[]{1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}}, new int[][]{new int[]{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15}, new int[]{13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9}, new int[]{10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4}, new int[]{3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}}, new int[][]{new int[]{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9}, new int[]{14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6}, new int[]{4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14}, new int[]{11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}}, new int[][]{new int[]{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11}, new int[]{10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8}, new int[]{9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6}, new int[]{4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}}, new int[][]{new int[]{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1}, new int[]{13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6}, new int[]{1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2}, new int[]{6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}}, new int[][]{new int[]{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7}, new int[]{1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2}, new int[]{7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8}, new int[]{2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}}};
    static final int[] e_r_p = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};
    static final int[] iip_tab_p = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};
    static final int[] local_PP = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};
    static final int[] ls_countp = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
    static final int[] pc_1_cp = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36};
    static final int[] pc_1_dp = {63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};
    static final int[] pc_2p = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
    byte[][] C = ((byte[][]) Array.newInstance(byte.class, 17, 28));
    byte[][] D = ((byte[][]) Array.newInstance(byte.class, 17, 28));
    byte[][] K = ((byte[][]) Array.newInstance(byte.class, 17, 48));
    public String digestHexStr;

    private static int b2iu(byte b) {
        return b < 0 ? b & 255 : b;
    }

    private static byte iu2b(int i) {
        return (byte) (i & 255);
    }

    public static String byteHEX(byte b) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        return new String(new char[]{cArr[(b >>> 4) & 15], cArr[b & TTypeStaticByteValueAllocationCl.m]});
    }

    private void desMemcpy(byte[] bArr, byte[] bArr2, int i, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            bArr[i + i4] = bArr2[i2 + i4];
        }
    }

    private void Fexpand0(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < 8; i++) {
            int i2 = 7;
            for (int i3 = 0; i3 < 8; i3++) {
                bArr2[(i * 8) + i3] = iu2b((b2iu(bArr[i]) >>> i2) & 1);
                i2--;
            }
        }
    }

    private void FLS(byte[] bArr, byte[] bArr2, int i) {
        for (int i2 = 0; i2 < 28; i2++) {
            bArr2[i2] = bArr[(i2 + i) % 28];
        }
    }

    private void Fson(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i;
        byte[] bArr4 = new byte[56];
        int i2 = 0;
        while (true) {
            if (i2 >= 28) {
                break;
            }
            bArr4[i2] = bArr[i2];
            i2++;
        }
        for (i = 28; i < 56; i++) {
            bArr4[i] = bArr2[i - 28];
        }
        for (int i3 = 0; i3 < 48; i3++) {
            bArr3[i3] = bArr4[pc_2p[i3] - 1];
        }
    }

    private void Fsetkeystar(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 28; i2++) {
            this.C[0][i2] = bArr[pc_1_cp[i2] - 1];
        }
        for (int i3 = 0; i3 < 28; i3++) {
            this.D[0][i3] = bArr[pc_1_dp[i3] - 1];
        }
        while (i < 16) {
            byte[][] bArr2 = this.C;
            int i4 = i + 1;
            FLS(bArr2[i], bArr2[i4], ls_countp[i]);
            byte[][] bArr3 = this.D;
            FLS(bArr3[i], bArr3[i4], ls_countp[i]);
            Fson(this.C[i4], this.D[i4], this.K[i4]);
            i = i4;
        }
    }

    private void Fiip(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[64];
        byte[] bArr5 = new byte[64];
        Fexpand0(bArr, bArr4);
        for (int i = 0; i < 32; i++) {
            bArr2[i] = bArr4[iip_tab_p[i] - 1];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            bArr3[i2] = bArr4[iip_tab_p[i2 + 32] - 1];
        }
    }

    private void Fs_box(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[8];
        int i = 0;
        int i2 = 0;
        while (i < 8) {
            int i3 = i * 6;
            bArr3[i] = iu2b(ccom_SSS_p[i][(b2iu(bArr[i3]) * 2) + b2iu(bArr[i3 + 5])][(b2iu(bArr[i3 + 1]) * 8) + (b2iu(bArr[i3 + 2]) * 4) + (b2iu(bArr[i3 + 3]) * 2) + b2iu(bArr[i3 + 4])]);
            int i4 = i2;
            int i5 = 0;
            int i6 = 3;
            while (i5 < 4) {
                bArr2[i4] = iu2b((b2iu(bArr3[i]) >>> i6) & 1);
                i6--;
                i5++;
                i4++;
            }
            i++;
            i2 = i4;
        }
    }

    private void FF(int i, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        byte[] bArr5 = new byte[64];
        byte[] bArr6 = new byte[64];
        for (int i2 = 0; i2 < 48; i2++) {
            bArr5[i2] = bArr2[e_r_p[i2] - 1];
        }
        for (int i3 = 0; i3 < 48; i3++) {
            bArr5[i3] = iu2b((b2iu(bArr5[i3]) + b2iu(this.K[i][i3])) & 1);
        }
        Fs_box(bArr5, bArr6);
        for (int i4 = 0; i4 < 32; i4++) {
            bArr5[i4] = bArr6[local_PP[i4] - 1];
        }
        for (int i5 = 0; i5 < 32; i5++) {
            bArr4[i5] = iu2b((b2iu(bArr5[i5]) + b2iu(bArr[i5])) & 1);
        }
        for (int i6 = 0; i6 < 32; i6++) {
            bArr3[i6] = bArr2[i6];
        }
    }

    private void _Fiip(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int i;
        byte[] bArr4 = new byte[64];
        int i2 = 0;
        while (true) {
            if (i2 >= 32) {
                break;
            }
            bArr4[i2] = bArr2[i2];
            i2++;
        }
        for (i = 32; i < 64; i++) {
            bArr4[i] = bArr3[i - 32];
        }
        for (int i3 = 0; i3 < 64; i3++) {
            bArr[i3] = bArr4[_iip_tab_p[i3] - 1];
        }
    }

    private void Fcompress016(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < 16; i++) {
            bArr2[i] = 48;
            int i2 = 3;
            for (int i3 = 0; i3 < 4; i3++) {
                bArr2[i] = iu2b(b2iu(bArr2[i]) + (b2iu(bArr[(i * 16) + i3]) << i2));
                i2--;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void Fcompress0(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < 8; i++) {
            bArr2[i] = 0;
            int i2 = 7;
            for (int i3 = 0; i3 < 8; i3++) {
                bArr2[i] = iu2b(b2iu(bArr2[i]) + (b2iu(bArr[(i * 8) + i3]) << i2));
                i2--;
            }
        }
    }

    private void Fencrypt0(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[64];
        byte[] bArr5 = new byte[64];
        byte[] bArr6 = new byte[64];
        byte[] bArr7 = new byte[64];
        Fiip(bArr, bArr3, bArr4);
        for (int i = 1; i < 17; i++) {
            FF(i, bArr3, bArr4, bArr5, bArr6);
            for (int i2 = 0; i2 < 32; i2++) {
                bArr3[i2] = bArr5[i2];
                bArr4[i2] = bArr6[i2];
            }
        }
        _Fiip(bArr7, bArr4, bArr3);
        Fcompress0(bArr7, bArr2);
    }

    private void FDES(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[64];
        Fexpand0(bArr, bArr4);
        Fsetkeystar(bArr4);
        Fencrypt0(bArr2, bArr3);
    }

    public int ENCRYPT(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        byte[] bArr4 = new byte[8];
        byte[] bArr5 = new byte[8];
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 8;
            if (i3 > i) {
                int i4 = i - i2;
                desMemcpy(bArr4, bArr2, 0, i2, i4);
                while (i4 < 8) {
                    bArr4[i4] = 0;
                    i4++;
                }
            } else {
                desMemcpy(bArr4, bArr2, 0, i2, 8);
            }
            FDES(bArr, bArr4, bArr5);
            desMemcpy(bArr3, bArr5, i2, 0, 8);
            i2 = i3;
        }
        return i2;
    }

    private void Fdiscrypt0(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[64];
        byte[] bArr4 = new byte[64];
        byte[] bArr5 = new byte[64];
        byte[] bArr6 = new byte[64];
        byte[] bArr7 = new byte[64];
        Fiip(bArr, bArr3, bArr4);
        for (int i = 16; i > 0; i--) {
            FF(i, bArr3, bArr4, bArr5, bArr6);
            for (int i2 = 0; i2 < 32; i2++) {
                bArr3[i2] = bArr5[i2];
                bArr4[i2] = bArr6[i2];
            }
        }
        _Fiip(bArr7, bArr4, bArr3);
        Fcompress0(bArr7, bArr2);
    }

    private void _FDES(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4 = new byte[64];
        Fexpand0(bArr, bArr4);
        Fsetkeystar(bArr4);
        Fdiscrypt0(bArr2, bArr3);
    }

    public int DECRYPT(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        byte[] bArr4 = new byte[8];
        byte[] bArr5 = new byte[8];
        int i2 = 0;
        while (i2 < i) {
            desMemcpy(bArr5, bArr3, 0, i2, 8);
            _FDES(bArr, bArr5, bArr4);
            desMemcpy(bArr2, bArr4, i2, 0, 8);
            i2 += 8;
        }
        return i2;
    }

    public static byte[] hexStr2ByteArr(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) Integer.parseInt(new String(bytes, i, 2), 16);
        }
        return bArr;
    }

    public String Encode(String str, String str2) {
        byte[] bArr = new byte[5000];
        byte[] bytes = str.getBytes();
        int ENCRYPT = ENCRYPT(str2.getBytes(), bytes, bArr, bytes.length);
        String str3 = "";
        for (int i = 0; i < ENCRYPT; i++) {
            str3 = str3 + byteHEX(bArr[i]);
        }
        return str3;
    }

    public String Decode(String str, String str2) {
        int length = str.getBytes().length;
        byte[] bArr = new byte[length];
        byte[] bArr2 = new byte[length];
        byte[] hexStr2ByteArr = hexStr2ByteArr(str);
        return new String(bArr, 0, DECRYPT(str2.getBytes(), bArr, hexStr2ByteArr, hexStr2ByteArr.length)).trim();
    }
}
