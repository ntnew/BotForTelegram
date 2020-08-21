package telegram.entity;

public class User {
    private long chatId;
    private String userName;
    private String realName;
    private String phone;

    public User(long chatId, String userName, String realName, String phone) {
        this.chatId = chatId;
        this.userName = userName;
        this.realName = realName;
        this.phone = phone;
    }

    public User() {
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
