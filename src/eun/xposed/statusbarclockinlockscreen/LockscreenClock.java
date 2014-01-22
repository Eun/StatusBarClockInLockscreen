package eun.xposed.statusbarclockinlockscreen;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class LockscreenClock implements IXposedHookLoadPackage  {
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		if (!lpparam.packageName.equals("com.android.systemui"))
	        return;
		
		final Class<?> PhoneStatusBarClass = XposedHelpers.findClass("com.android.systemui.statusbar.phone.PhoneStatusBar", lpparam.classLoader);
		XposedHelpers.findAndHookMethod(PhoneStatusBarClass, "showClock", boolean.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(final MethodHookParam param) throws Throwable {
                if ((Boolean)param.args[0] == false)
                {
                	param.args[0] = true;
                }
            }
        });
    }
}