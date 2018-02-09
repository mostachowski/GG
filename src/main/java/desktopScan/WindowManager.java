package desktopScan;


import static desktopScan.WindowManager.User32DLL.*;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;

import com.sun.jna.ptr.PointerByReference;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WindowManager {
    private static final int MAX_TITLE_LENGTH = 1024;


    public int[] GetForeGroundWindowRect()
    {
        int[] rect = {0,0,0,0};
        GetWindowRect(GetForegroundWindow(),rect);

        return rect;
    }

    public Boolean Scale(BufferedImage image, int width, int height)
    {
        if (width/height != image.getWidth()/image.getHeight())
        return false;

        if (width != image.getWidth())
        {
            image = toBufferedImage( image.getScaledInstance(width,height, Image.SCALE_DEFAULT));
        }
        return true;
    }

    private  BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }


    static class Psapi {
        static {
            Native.register("psapi");
        }

        public static native int GetModuleBaseNameW(Pointer hProcess, Pointer hmodule, char[] lpBaseName, int size);
    }

    static class Kernel32 {
        static {
            Native.register("kernel32");
        }

        public static int PROCESS_QUERY_INFORMATION = 0x0400;
        public static int PROCESS_VM_READ = 0x0010;

        public static native int GetLastError();

        public static native Pointer OpenProcess(int dwDesiredAccess, boolean bInheritHandle, Pointer pointer);
    }


    static class User32DLL {
        static {
            Native.register("user32");
        }

       public static native int GetWindowThreadProcessId(HWND hWnd, PointerByReference pref);

        public static native HWND GetForegroundWindow();

        public static native int GetWindowTextW(HWND hWnd, char[] lpString, int nMaxCount);

        public static native  int GetWindowRect(HWND hWnd, int[] rect);
    }
}