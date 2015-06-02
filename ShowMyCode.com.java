package com.drcom.util;


public class Encrypt
{

    private Encrypt()
    {
    }

    public static String Decipher(String from_text)
    {
        char word[] = from_text.toCharArray();
        String to_text = "";
        long key = NumericPassword(KEYWORD);
        int str_len = from_text.length() - 1;
        for(int i = 0; i < str_len; i++)
        {
            word[i] = from_text.charAt(i);
            int ch = word[i];
            if(ch >= MIN_ASC && ch <= MAX_ASC)
            {
                i++;
                ch -= MIN_ASC;
                double offset = (double)(NUM_ASC + 1) * ((double)((key * (long)i) % MYPRIMENUMBER) / (double)MYPRIMENUMBER);
                ch = (ch - (int)offset) % NUM_ASC;
                if(ch < 0)
                    ch += NUM_ASC;
                ch += MIN_ASC;
                i--;
                to_text = (new StringBuilder(String.valueOf(to_text))).append((char)ch).toString();
            }
        }

        return to_text;
    }

    public static String Cipher(String from_text)
    {
        char word[] = from_text.toCharArray();
        String to_text = "";
        long key = NumericPassword(KEYWORD);
        int str_len = from_text.length() - 1;
        for(int i = 0; i <= str_len; i++)
        {
            word[i] = from_text.charAt(i);
            int ch = word[i];
            if(ch >= MIN_ASC && ch <= MAX_ASC)
            {
                i++;
                ch -= MIN_ASC;
                double offset = (double)(NUM_ASC + 1) * ((double)((key * (long)i) % MYPRIMENUMBER) / (double)MYPRIMENUMBER);
                ch = (ch + (int)offset) % NUM_ASC;
                ch += MIN_ASC;
                i--;
                to_text = (new StringBuilder(String.valueOf(to_text))).append((char)ch).toString();
            }
        }

        return (new StringBuilder(String.valueOf(to_text))).append("a").toString();
    }

    public static long NumericPassword(String password)
    {
        long shift1 = 0L;
        long shift2 = 0L;
        long value = 0L;
        int str_len = password.length();
        for(int i = 0; i < str_len; i++)
        {
            long ch = password.charAt(i);
            value ^= ch * MyIndex(shift1);
            value ^= ch * MyIndex(shift2);
            shift1 = (shift1 + 7L) % 19L;
            shift2 = (shift2 + 13L) % 23L;
        }

        value = (value ^ MYPRIMENUMBER2) % MYPRIMENUMBER;
        return value;
    }

    public static long MyIndex(long shadow)
    {
        long j = 1L;
        for(long i = 1L; i <= shadow; i++)
            j *= 2L;

        return j;
    }

    private static int MIN_ASC;
    private static int MAX_ASC;
    private static int NUM_ASC;
    private static long MYPRIMENUMBER = 0x188b9L;
    private static long MYPRIMENUMBER2 = 0x18901L;
    private static String KEYWORD = "TblRefreshCurMonthServiceUse";

    static 
    {
        MIN_ASC = 32;
        MAX_ASC = 126;
        NUM_ASC = (MAX_ASC - MIN_ASC) + 1;
    }
}