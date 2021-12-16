package com.example.rhomie.Objects;
import android.os.Build;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

public class Item {

    private int item_id;
    private Address address;
    private Flags flags;
    private String check_in;
    private String check_out;
    private String guest_number;
    private String dateFormat;

    /* Default Constructor */
    public Item () {
        item_id = 0;
        address = null;
        flags = null;
        check_in = null;
        check_out = null;
        guest_number = "";
    }

    /* Full Constructor */
    public Item (int i,Address a,Flags f,String ci, String co, String gn) {
        this.item_id = i;
        this.address = a;
        this.flags = f;
        this.check_in = ci;
        this.check_out = co;
        this.guest_number = gn;
    }

    /* Item ID */
    public int getItem () {
        return this.item_id;
    }

    /* Address */
    public Address getAddress () {
        return this.address;
    }

    public void setAddress (String c,String s,String sn,String f, String an) {
        this.address = new Address(c ,s ,sn ,f ,an);
    }

    /* Flags */
    public Flags getFlags () {
        return this.flags;
    }

    public void setFlags (boolean k, boolean an, boolean ac, boolean p, boolean s, boolean wi) {
        this.flags = new Flags(k, an, ac, p, s, wi);
    }

    /* Check In */
    public String getCheckIn () {
        return this.check_in;
    }

    public void setCheckIn (String in) {
        this.check_in = in;
    }

    /* Check Out */
    public String getCheckOut () {
        return this.check_out;
    }

    public void setCheckOut (String out) {
        this.check_out = out;
    }

    /* Guest Number */
    public String getGuestNumber () {
        return this.guest_number;
    }

    public void setGuestNumber (String gn) {
        this.guest_number = gn;
    }

    public HashMap<String,Object> itemToMap () {
        HashMap<String, Object> item = new HashMap<>();
        item.put("item_id", this.item_id);
        item.put("address", this.address);
        item.put("flags", this.flags);
        item.put("check_in", this.check_in);
        item.put("check_out", this.check_out);
        item.put("guest_number", this.guest_number);
        return item;
    }

    public int isValid() {
        if(this == null)
            return 0;
        if (getCheckIn().isEmpty())
            return 1;
        if(getCheckOut().isEmpty())
            return 2;
        if(getGuestNumber().length() == 0 || getGuestNumber().length() > 2 || !onlyDigit(getGuestNumber()))
            return 3;
        if(getAddress().getCity().length() < 2 || !onlyAlphabetic(getAddress().getCity()))
            return 4;
        if(getAddress().getStreet().length() < 2 || !onlyAlphabetic(getAddress().getStreet()))
            return 5;
        if(getAddress().getStreetNumber().length() == 0 || !onlyDigit(getAddress().getStreetNumber()))
            return 6;
        if(!isGreater(getCheckIn()))
            return 7;
        if(!isValidDate(getCheckIn(),getCheckOut()))
            return 8;
        return -1;
    }

    private boolean onlyAlphabetic(String s) {
        char[] chars = s.toCharArray();
        for(char c : chars){
            if(Character.isDigit(c))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id=" + item_id +
                ", address=" + address +
                ", flags=" + flags +
                ", check_in='" + check_in + '\'' +
                ", check_out='" + check_out + '\'' +
                ", guest_number='" + guest_number + '\'' +
                ", dateFormat='" + dateFormat + '\'' +
                '}';
    }

    private boolean onlyDigit(String s) {
        char[] chars = s.toCharArray();
        for(char c : chars){
            if(!Character.isDigit(c))
                return false;
        }
        return true;
    }

    public boolean isValidDate(String check_in, String check_out)  {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dateIn = null;
        Date dateOut = null;
        try {
            dateIn = sdf.parse(check_in);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dateOut = sdf.parse(check_out);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(dateOut.after(dateIn))
            return true;

        return false;
    }
    public boolean isGreater(String dateStr)  {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date.after(currentDate))
            return true;
        return false;
    }
}
