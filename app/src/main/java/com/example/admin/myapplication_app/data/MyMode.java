package com.example.admin.myapplication_app.data;

import java.util.Objects;

/**
 * Discription:
 * Created by guokun on 2019/5/22.
 */
public class MyMode {
    private long id;
    private String name;
    private String dis;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMode myMode = (MyMode) o;
        return id == myMode.id &&
                name.equals(myMode.name) &&
                dis.equals(myMode.dis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dis);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }
}
