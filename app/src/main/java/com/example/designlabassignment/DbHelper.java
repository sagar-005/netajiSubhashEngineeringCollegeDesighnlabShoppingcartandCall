package com.example.designlabassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.designlabassignment.modals.OrderModal;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

public class DbHelper extends SQLiteOpenHelper {


    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DBNAME ,null,DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table orders "+
                        "(id integer primary key autoincrement," +
                        "name text,"+
                        "phone text,"+
                        "price int,"+
                        "image int,"+
                        "description text,"+
                        "foodname text ," +
                        "quantity int )"
        );

    }
    /*
    id 0
    name 1
    phone 2
    price  3
    image 4
    desc 5
    foodname 6
     quantity 7
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists orders");
        onCreate(db);
    }
    public boolean insertOrder(String name ,String phone, int price,int image,String description,String foodname,int quantity){

        SQLiteDatabase db =getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name",name);
        cv.put("phone",phone);
        cv.put("price",price);
        cv.put("image",image);
        cv.put("description",description);
        cv.put("foodname",foodname);
        cv.put("quantity",quantity);

        long id = db.insert("orders",null,cv);

        return id<=0? false: true;
    }
    public boolean updateOrder(String name ,String phone, int price,int image,String description,String foodname,int quantity,int id){

        SQLiteDatabase db =getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("name",name);
        cv.put("phone",phone);
        cv.put("price",price);
        cv.put("image",image);
        cv.put("description",description);
        cv.put("foodname",foodname);
        cv.put("quantity",quantity);

        long row = db.update("orders",cv,"id="+id,null);

        return row<=0? false: true;
    }
    public ArrayList<OrderModal> getOrders(){
        ArrayList<OrderModal> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cr = database.rawQuery("select id,price,foodname,image from orders" ,null);
        if(cr.moveToFirst())
        {
            while(cr.moveToNext()){
                OrderModal modal = new OrderModal();
                modal.setOrdernumber(cr.getInt(0)+"");
                modal.setImage(cr.getInt(3));
                modal.setOrdername(cr.getInt(2)+"");
                modal.setOrderprice(cr.getInt(1)+"");
                  orders.add(modal);
            }
        }
        cr.close();
        database.close();
        return orders;
    }
    public Cursor getOrderbyId(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from orders where id = "+ id,null);
        if(cursor!=null) cursor.moveToFirst();

        return cursor;
    }
    public int deleteOrder(String id){
        SQLiteDatabase database =this.getWritableDatabase();
        return database.delete("orders","id="+id,null);

    }
}
