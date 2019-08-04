package com.batticaloa360.wapp;

public class DB {

    public static String[] getData(int id)
    {
        if (id == R.id.action_eng_ta) {
           return  getEngTa();
        }else if(id==R.id.action_ta_eng)
        {
            return getTaEng();
        }else if(id==R.id.action_ta_ta)
        {
            return getTaTa();
        }

        return new String[0];
    }

    public static String[] getEngTa()
    {
        String[] source = new String[]
                {
                        "a",
                        "an",
                        "ability",
                        "able",
                        "about",
                        "above",
                        "abroad",
                        "absent",
                        "absolutely",
                        "awful",
                        "accent",
                        "accept",
                        "access",
                        "accident",
                        "accommodation",
                        "accompany",
                        "according to",
                        "account",
                        "accountant",
                        "accurate",
                        "ache"
                };

                return  source;
    }


    public static String[] getTaEng()
    {
        String[] source = new String[]
                {
                        "அ",
                        "அம்மா",
                        "அப்பா",
                        "ஆடு",
                        "ஆறு",
                        "இடியப்பம்",
                        "ஈ",
                        "உரல்",
                        "ஊடு",
                        "ஏணி",
                        "எறும்பு",
                        "அரசன்"
                };

        return  source;
    }


    public static String[] getTaTa()
    {
        String[] source = new String[]
                {
                        "அ",
                        "அம்மா",
                        "அப்பா",
                        "ஆடு",
                        "ஆறு",
                        "இடியப்பம்",
                        "ஈ",
                        "உரல்",
                        "ஊடு",
                        "ஏணி",
                        "எறும்பு",
                        "அரசன்"


                };

        return  source;
    }
}


