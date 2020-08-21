package telegram.entity;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private int id;
    private long chatId;
    private String realName;
    private String notice;
    private String address;
    private String phone;
    private String products;
    private double price;
    private String type;
    private String pay;
    private Date date;
    private Time time;


    public Order(int id, long chatId, String realName, String notice, String address, String phone, String products, double price, String type, String pay, Date date, Time time) {
        this.id = id;
        this.chatId = chatId;
        this.realName = realName;
        this.notice = notice;
        this.address = address;
        this.phone = phone;
        this.products = products;
        this.price = price;
        this.type = type;
        this.pay = pay;
        this.date = date;
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Order() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return realName + ", Ваш заказ №" +id + ": \n" + products +
                "\nПолная стоимость = " + price + " руб." +
                "\nПримечание:" + notice +
                "\n" + type + " по адресу " + address +
                "\nКонтактный телефон " + phone +
                "\nОплата " + pay;
    }

    public String toFullString() {
        return realName + ", Ваш заказ №" +id + ": \n" + products +
                "\nПолная стоимость = " + price + " руб." +
                "\nПримечание:" + notice +
                "\n" + type + " по адресу " + address +
                "\nКонтактный телефон " + phone +
                "\nОплата " + pay +
                "\n" + date + " " + time;
    }

    public String toServiceString() {
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy 'и время' kk:mm:ss ");
        return "Имя: " + realName + "\nЗаказ №" +id +
                ": \n" + products +
                "\nПолная стоимость = " + price + " руб." +
                "\nПримечание:" + notice +
                "\n" + type + " по адресу " + address +
                "\nКонтактный телефон " + phone +
                "\nОплата " + pay +
                "\n" + formatForDateNow.format(date) +
                "\nЗаказ из telegram. \nПерезвоните по контактному телефону для подтверждения заказа";
    }
}
