package com.process.domain.document2;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class BooleanAdapter extends XmlAdapter<String, Boolean> {
	
    @Override
    public Boolean unmarshal(String s) throws Exception {
      return "S".equals(s)||"true".equals(s);
    }

    @Override
    public String marshal(Boolean b) throws Exception {
      if (b) {
        return "true";
      }
      return "false";
    }

}
