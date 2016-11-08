package com.learning.apl.apllearning;

/**
 * Created by shiva on 1/11/16.
 */

public class AppConstants {

    public static final String HOST_NAME = "http://192.168.0.6/apllearning/";
    public static final String MATERIAL_URL = HOST_NAME + "storage/app/public/";
    public static final String LOGIN_URL = "student/login";
    public static final String LOGOUT_URL = "student/logout";
    public static final String CHANGE_PASSWORD_URL = "user/changePassword";
    public static final String GET_LOGGED_IN_USER_URL = "user/getLoggedInUser";
    public static final String GET_SUBJECTS_URL = "admin/getSubjectsList";
    public static final String GET_MATERIALS_URL = "teacher/getMaterialsList";

    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token";

    public static final String OLD_PASSWORD = "old_password";
    public static final String NEW_PASSWORD = "new_password";

    public static final String GRADE = "grade";
    public static final String GRADE_ID = "grade_id";
    public static final String SUBJECT_ID = "subject_id";
    public static final String START = "start";
    public static final String SIZE = "size";

    public static final int MAX_VALUE = 100;
    public static final int INITIAL_START_VALUE = 0;
    public static final int SIZE_VALUE = 10;

    public static final String USER_PREF = "userPref";
    public static final String IS_USER_LOGGED_IN = "isUserLoggedIn";
    public static final String USER_ID = "user_id";
}
