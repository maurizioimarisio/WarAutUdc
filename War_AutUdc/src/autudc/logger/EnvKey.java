package autudc.logger;

 interface EnvKey {

    // Costanti relative alle chiavi della componente logger
    static final String LOG_ALIAS_NAMES              = "LogAliasNames";
    static final String LOG_ROOT_NAME                = "ROOT";
    static final String LOG_DEFAULT_DELIMITER        = ",";
    static final String LOG_LEVEL                    = ".level";
    static final String LOG_DEFAULT_DEVICE           = ".defaultDevice";
    static final String LOG_FILE_DEVICE              = "FILE";
    static final String LOG_SYSTEM_OUT_DEVICE        = "SYSOUT";
    static final String LOG_SYSTEM_ERR_DEVICE        = "SYSERR";
    static final String LOG_SYSTEM_NT_CONSOLE_DEVICE = "NT_EVENTLOG";
    static final String LOG_PATH_FILE                = ".pathFile";
    static final String LOG_DEFAULT_PATTERN_LAYOUT   = ".defaultPatternLayout";
    static final String LOG_OPERATING_SYSTEM         = ".operatingSystem";

    static final String LOG_LEVEL_1                  = "logLevel1";
    static final String LOG_LEVEL_2                  = "logLevel2";
    static final String LOG_LEVEL_3                  = "logLevel3";

    static final String LOG_LABEL_1                  = "label1";
    static final String LOG_LABEL_2                  = "label2";
    static final String LOG_LABEL_3                  = "label3";
}
