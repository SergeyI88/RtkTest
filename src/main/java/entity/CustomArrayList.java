package entity;


import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class CustomArrayList<E> extends ArrayList<Tag>  {

    private int count;
    private Tag[] objects;

    public CustomArrayList() {
        super();
    }

    @Override
    public boolean add(Tag o) {
        boolean res = super.add(o);
        if (res) {
            count++;
        }
        return res;
    }

    public Object[] getObjects() {
        return objects;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public void setObjects(Tag[] objects) {
        this.objects = objects;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
