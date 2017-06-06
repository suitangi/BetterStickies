import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Component comp;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame, Component com) {
            this.frame = frame;
            comp = com;
        }
        public static boolean isMouseWithinComponent(Component c)
        {
            Point mousePos = MouseInfo.getPointerInfo().getLocation();
            Rectangle bounds = c.getBounds();
            bounds.setLocation(c.getLocationOnScreen());
            return bounds.contains(mousePos);
        }
       
        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            if(isMouseWithinComponent(comp))
                mouseDownCompCoords = e.getPoint();
            else
                mouseDownCompCoords = null;
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            if(mouseDownCompCoords != null)
                frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }