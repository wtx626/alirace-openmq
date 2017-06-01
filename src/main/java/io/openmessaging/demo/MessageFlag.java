package io.openmessaging.demo;

public class MessageFlag {

    //Delimiter
    public static final byte MESSAGE_START = -1; //^
    public static final byte MESSAGE_END = -2; //$
    public static final byte KEY_END = -3; //:
    public static final byte VALUE_END = -4; //;
    public static final byte FIELD_END = -5; //|

    //Headers Key
    public static final byte QUEUE = 1;
    public static final byte TOPIC = 2;
    public static final byte MESSAGE_ID = 3;
    public static final byte BORN_HOST = 4;
    public static final byte BORN_TIMESTAMP = 5;
    public static final byte PRIORITY = 6;
    public static final byte RELIABILITY = 7;
    public static final byte SCHEDULE_EXPRESSION = 8;
    public static final byte SEARCH_KEY = 9;
    public static final byte SHARDING_KEY = 10;
    public static final byte SHARDING_PARTITION = 11;
    public static final byte START_TIME = 12;
    public static final byte STOP_TIME = 13;
    public static final byte STORE_HOST = 14;
    public static final byte STORE_TIMESTAMP = 15;
    public static final byte TIMEOUT = 16;
    public static final byte TRACE_ID = 17;

    //Properties Key
    public static final byte PRO_OFFSET = 18;

    public static final String TOPIC_STR_PREFIX = "TOPIC_";
    public static final String QUEUE_STR_PREFIX = "QUEUE_";
    public static final String PRODUCER_STR_PREFIX = "PRODUCER";

    public static final byte TOPIC_PREFIX = 19;
    public static final byte QUEUE_PREFIX = 20;
    public static final byte PRODUCER_PREFIX = 21;

}