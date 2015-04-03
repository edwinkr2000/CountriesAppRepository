package com.netspacekenya.leftie.countries;

import android.os.Bundle;

import java.io.Serializable;

/**
 * Created by Edwin on 16-Mar-15.
 */
public class Country implements Serializable {
    private int index;
    private String commonName;
    private String formalName;
    private String type;
    private String subType;
    private String sovereignity;
    private String capital;
    private String currencyCode;
    private String currencyName;
    private String telephoneCode;
    private String letterCode;
    private String letterCode2;
    private int number;
    private String tldCode;

    public Country( int index,
            String commonName,
            String formalName,
            String type,
            String subType,
            String sovereignity,
            String capital,
            String currencyCode,
            String currencyName,
            String telephoneCode,
            String letterCode,
            String letterCode2,
            int number,
            String tldCode){
        this.index=index;
        this.commonName=commonName;
        this.formalName=formalName;
        this.type=type;
        this.subType=subType;
        this.sovereignity=sovereignity;
        this.capital=capital;
        this.currencyCode=currencyCode;
        this.currencyName=currencyName;
        this.telephoneCode=telephoneCode;
        this.letterCode=letterCode;
        this.letterCode2=letterCode2;
        this.number=number;
        this.tldCode=tldCode;

    }
    public int getIndex() {
        return index;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getFormalName() {
        return formalName;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getSovereignity() {
        return sovereignity;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getCapital() {
        return capital;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getTelephoneCode() {
        return telephoneCode;
    }

    public String getLetterCode() {
        return letterCode;
    }

    public String getLetterCode2() {
        return letterCode2;
    }

    public int getNumber() {
        return number;
    }

    public String getTldCode() {
        return tldCode;
    }
    public Bundle toBundle(){
        Bundle b = new Bundle();
        b.putInt("index",index);
        b.putString("common name", commonName);
        b.putString("formal name", formalName);
        b.putString("type", type);
        b.putString("sub-type", subType);
        b.putString("sovereignity", sovereignity);
        b.putString("capital", capital);
        b.putString("currency name", currencyName);
        b.putString("currency code",currencyCode);
        b.putString("telephoneCode code", telephoneCode);
        b.putString("letter code 1", letterCode);
        b.putString("letter code 2", letterCode2);
        b.putInt("number", number);
        b.putString("tld code", tldCode);

        return b;

    }
public String toString(){
    return commonName;
}
}
