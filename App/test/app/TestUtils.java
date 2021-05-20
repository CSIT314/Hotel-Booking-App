package app;

import java.awt.*;
import javax.swing.*;

public class TestUtils {

    static int counter;

    public static Component getChildNamed(Component parent, String name) {

        if (name.equals(parent.getName())) { 
            return parent; 
        }

        if (parent instanceof Container) {
            Component[] childern = (parent instanceof JMenu) ?
                    ((JMenu) parent).getMenuComponents() :
                    ((Container)parent).getComponents();
            for (int i=0; i < childern.length; ++i) {
                Component child = getChildNamed(childern[i], name);
                if (child != null) {return child; }
            }
        }

        return null;
    }

    public static Component getChildIndexed(Component parent, String name, int index) {
        counter = 0;
        
        if (parent instanceof Window) {
            Component[] childern = ((Window)parent).getOwnedWindows();

            for (int i=0; i<childern.length; ++i) {
                if (childern[i] instanceof Window &&
                    !((Window)childern[1]).isActive()) { continue; }
                Component child = getChildIndexedInternal(childern[i], name, index);
                if (child != null) {return child; }
            }
        }
        return null;
    }

    public static Component getChildIndexedInternal(Component parent, String name, int index) {
        
        if (parent.getClass().toString().endsWith(name)) {
            if (counter == index) { return parent; }
            ++counter;
        }
        if (parent instanceof Container) {
            Component[] childern = (parent instanceof JMenu) ?
                ((JMenu)parent).getMenuComponents():
                ((Container)parent).getComponents();

            for (int i=0; i<childern.length; ++i) {
                Component child = getChildIndexedInternal(
                    childern[i], name, index);
                if (child != null) {return child; }
            }
        }
        return null;
    }

}