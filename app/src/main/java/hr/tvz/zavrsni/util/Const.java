package hr.tvz.zavrsni.util;


public interface Const {

    // API
    String URL_BASE = "http://markovicdev.io";

    String API_CATEGORY = "/category/";
    String API_JOBS = "/category/{category_id}/jobs/";
    String API_JOB = "/category/{category_id}/jobs/{job_id}/";
    String API_USER = "/user/";
    String API_LOGIN = "/login/";

    // request header
    String USERNAME = "username";
    String PASSWORD = "password";

    // response code
    int ERROR_AUTH = -5;

    // JSON elements
    String SUCCESS = "success";
    String MESSAGE = "message";

    //user table
    String USER_NAME = "name";
    String USER_SURNAME = "surname";
    String USER_USERNAME = "username";
    String USER_PASSWORD = "password";
    String USER_EMAIL = "email";


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

}