package com.ajwalker.constants;

public class RestAPIs {
    private static final String VERSION = "/v1";
    private static final String API = "/api";
    private static final String DEVELOPER = "/dev";
    private static final String TEST = "/test";
    private static final String PROD = "/prod";

    private static final String ROOT = VERSION + DEVELOPER;

    //TODO: entitylere göre düzenle!
    public static final String DERS = ROOT + "/ders";
    public static final String DERSLIK = ROOT + "/derslik";
    public static final String IDARECI = ROOT + "/idareci";
    public static final String OGRENCI = ROOT + "/ogrenci";
    public static final String OGRETMEN = ROOT + "/ogretmen";
    public static final String SINIF = ROOT + "/sinif";


    public static final String ADD = "/add";
    public static final String FIND_ALL = "/find-all";
    public static final String FIND_BY_ID = "/find-by-id";
}
