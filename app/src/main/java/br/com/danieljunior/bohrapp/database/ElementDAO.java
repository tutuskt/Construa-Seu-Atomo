package br.com.danieljunior.bohrapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.danieljunior.bohrapp.models.Element;

/**
 * Created by danieljr on 07/02/17.
 */

public class ElementDAO {

    DataBaseHelper dbHelper;
    SQLiteDatabase db;

    public ElementDAO(Context context) throws IOException {
        dbHelper = new DataBaseHelper(context);
        open();
    }

    public Element byPN(int p, int n) {
        Cursor cursor = db.rawQuery("SELECT * from elementos_quimicos WHERE proton = ? and neutron = ?", new String[]{p + "", ""+n});
        if (cursor.moveToFirst()) {
            Element element = buildElement(cursor);
            return element;
        } else {
            return null;
        }
    }

    public List<Element> all() {
        List<Element> response = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * from elementos_quimicos", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Element element = buildElement(cursor);
                response.add(element);
                cursor.moveToNext();
            }
        }
        return response;
    }

    public void close() {
        dbHelper.close();
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    private Element buildElement(Cursor cursor) {
        Element element = new Element();
        element.setId(Long.valueOf(cursor.getString(cursor.getColumnIndex("_id"))));
        element.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        element.setSimbolo(cursor.getString(cursor.getColumnIndex("simbolo")));
        element.setA(Integer.valueOf(cursor.getString(cursor.getColumnIndex("A"))));
        element.setZ(Integer.valueOf(cursor.getString(cursor.getColumnIndex("Z"))));
        element.setNeutron(Integer.valueOf(cursor.getString(cursor.getColumnIndex("neutron"))));
        element.setProton(Integer.valueOf(cursor.getString(cursor.getColumnIndex("proton"))));
//        element.setEletron(cursor.getString(cursor.getColumnIndex("eletron")));
        element.setMensagem(cursor.getString(cursor.getColumnIndex("mensagem")));
        return element;
    }
}
