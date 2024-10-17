package com.ajwalker.constans;

public class RestAPIs {
    private static final String VERSION = "/v1";
    private static final String API = "/api";
    private static final String DEVELOPER = "/dev";
    private static final String TEST = "/test";
    private static final String PROD = "/prod";

    private static final String ROOT = VERSION + DEVELOPER;

    public static final String FOLLOW = ROOT + "/follow";
    public static final String MESSAGE = ROOT + "/message";
    public static final String USER = ROOT + "/user";


    public static final String ADD = "/add";
    public static final String FIND_ALL = "/find-all";
    public static final String FIND_ALL_FEMALE = "/find-all-f";
    public static final String FIND_BY_ID = "/find-by-id";
    public static final String FIND_BY_USERNAME = "/search";
        public static final String LOGIN = "/login";
        public static final String REGISTER = "/register";
        public static final String ADD_FOLLOW = "/add-follow";
}
