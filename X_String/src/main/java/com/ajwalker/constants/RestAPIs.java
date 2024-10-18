package com.ajwalker.constants;

public class RestAPIs {
    private static final String VERSION = "/v1";
    private static final String API = "/api";
    private static final String DEVELOPER = "/dev";
    private static final String TEST = "/test";
    private static final String PROD = "/prod";

    private static final String ROOT = VERSION + DEVELOPER;

    public static final String USER = ROOT + "/user";
    public static final String MUSTERI = ROOT + "/musteri";
    public static final String ODA = ROOT + "/oda";

    public static final String ADD = "/add";
    public static final String FIND_ALL = "/find-all";
    public static final String FIND_BY_ID = "/find-by-id";

    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String GET_PROFILE = "/profile";

}
