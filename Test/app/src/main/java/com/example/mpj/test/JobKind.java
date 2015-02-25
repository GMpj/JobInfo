package com.example.mpj.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mpj on 15-2-13.
 * 尝试将维护性的两列数组替换成map
 */
public class JobKind {
    private Map<String,String> kind=new HashMap<String, String>();
    public JobKind(){
        kind.put("互联网/电子商务","in=210500");
    }
}
