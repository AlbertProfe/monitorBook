package com.example.demo.model;

public class ActivityLog {

    private String id;
    private String ip;
    private String operationMethod;
    private String user;
    private long time; // Assuming time is represented as a Unix timestamp (long)
    private int status;
    private String endpoint;
    private String domain;

    // Constructors
    public ActivityLog() {
        // Default constructor
    }

    public ActivityLog(String id, String ip, String operationMethod, String user, long time, int status, String endpoint, String domain) {
        this.id = id;
        this.ip = ip;
        this.operationMethod = operationMethod;
        this.user = user;
        this.time = time;
        this.status = status;
        this.endpoint = endpoint;
        this.domain = domain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getters and Setters
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOperationMethod() {
        return operationMethod;
    }

    public void setOperationMethod(String operationMethod) {
        this.operationMethod = operationMethod;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "ActivityLog{" +
                "ip='" + ip + '\'' +
                ", operationMethod='" + operationMethod + '\'' +
                ", user='" + user + '\'' +
                ", time=" + time +
                ", status=" + status +
                ", endpoint='" + endpoint + '\'' +
                ", domain='" + domain + '\'' +
                '}';
    }
}
