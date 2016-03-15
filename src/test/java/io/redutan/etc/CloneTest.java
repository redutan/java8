package io.redutan.etc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author redutan
 * @since 2015. 10. 14.
 */
public class CloneTest {
    public static void main(String[] args) throws Exception {
        ValueObject vo1 = new ValueObject();         // heap 1
        vo1.str = "MJ";                              // static 1-1
        vo1.num = 1;                                // stack
        vo1.floats.add(1f);                         // heap 1-2

        ValueObject vo2 = (ValueObject)vo1.clone(); // heap 2
        System.out.println("vo2 = " + vo2);
        // vol2                                     // static 1-1
        vo2.str = "Ming";                           // static 2-1
        vo2.num = 2;                                // stack
        vo2.floats.add(2f);                         // heap 1-2

        System.out.println("vo1 = " + vo1);
        System.out.println("vo2 = " + vo2);

        System.out.println(vo1.floats.hashCode());
        System.out.println(vo2.floats.hashCode());
    }
}

class ValueObject implements Cloneable {
    String str;
    int num;
    List<Float> floats = new ArrayList<Float>();

    @Override
    public Object clone() throws CloneNotSupportedException {
//        return super.clone();
        ValueObject clone = (ValueObject) super.clone();
        clone.floats = new ArrayList<Float>(floats);
        return clone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("str = " + str + ", num = " + num + ", floats = ");
        for (Float aFloat : floats) {
            sb.append(aFloat).append(" | ");
        }
        return sb.toString();
    }

}
