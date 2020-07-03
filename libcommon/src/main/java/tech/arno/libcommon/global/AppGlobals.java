package tech.arno.libcommon.global;

import android.annotation.SuppressLint;
import android.app.Application;

import java.lang.reflect.InvocationTargetException;

/**
 * <pre>
 *     author: xuxin
 *     time  : 2020/7/2
 *     desc  :
 * </pre>
 */
public class AppGlobals {
    private static Application sApplication;

    @SuppressLint("PrivateApi")
    public static Application getApplication() {
        if (sApplication == null) {
            //反射入口得到
            try {
                sApplication = (Application) Class.forName("android.app.ActivityThread")
                        .getMethod("currentApplication")
                        .invoke(null, (Object[]) null);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return sApplication;
    }
}