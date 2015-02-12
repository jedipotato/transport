package hr.tvz.zavrsni.util;


public interface Const {

    // API
    String URL_BASE = "http://markovicdev.io";

    String API_CATEGORY = "/category/";
    String API_JOBS = "/category/{category_id}/jobs/";
    String API_JOB_BY_CATEGORY = "/category/{category_id}/jobs/{job_id}/";
    String API_JOB = "/category/jobs/{job_id}/";
    String API_BIDS = "/category/{category_id}/jobs/{job_id}/bids/";
    String API_USER = "/user/";
    String API_USER_USERNAME = "/user/{username}";
    String API_USER_JOBS = "/user/jobs/";
    String API_USER_BIDS = "/user/bids/";
    String API_LOGIN = "/login/";

    // request header
    String USERNAME = "username";
    String PASSWORD = "password";

    // response code
    int ERROR_AUTH = -5;

    // JSON elements
    String SUCCESS = "success";
    String MESSAGE = "message";
    String IS_USER = "is_user"; //true/false

    //user table
    String USER_NAME = "name";
    String USER_SURNAME = "surname";
    String USER_USERNAME = "username";
    String USER_PASSWORD = "password";
    String USER_EMAIL = "email";
    String USER_COMPANY = "company_name";
    String USER_CONTACT = "contact";


    //category table
    String CATEGORIES = "categories";
    String CATEGORY_ID = "category_id";
    String CATEGORY_NAME = "name";

    //jobs table
    String JOBS = "jobs";
    String JOB_ID = "job_id";
    String USER_ID = "user_id"; //same as user table id
    String JOB_NAME = "name";
    String JOB_DESCRIPTION = "description";
    String JOB_CREATION_DATE = "creation_date";
    String JOB_EXPIRATION_DATE = "expiration_date";
    String JOB_LOWEST_BID = "lowest_bid";
    String JOB_LOWEST_USER = "lowest_user";
    String JOB_DURATION = "duration";

    //bids table
    String BIDS = "bids";
    String BID_ID = "bid_id";
    String BID_BID = "bid";
    String BID_DESCRIPTION = "description";
    String BID_CREATION_DATE = "creation_date";
}