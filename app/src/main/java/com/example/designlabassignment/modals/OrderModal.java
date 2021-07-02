package com.example.designlabassignment.modals;

public class OrderModal {

    int image;
    String ordername,ordernumber,orderprice;

    public OrderModal()
    {

    }
    public OrderModal(int image, String ordername, String ordernumber, String orderprice) {
        this.image = image;
        this.ordername = ordername;
        this.ordernumber = ordernumber;
        this.orderprice = orderprice;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(String orderprice) {
        this.orderprice = orderprice;
    }
}
