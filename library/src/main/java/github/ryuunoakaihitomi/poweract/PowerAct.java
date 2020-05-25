package github.ryuunoakaihitomi.poweract;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.admin.DevicePolicyManager;
import android.os.Build;
import android.util.Log;

/**
 * Provide the main functions of the library.
 * <p>
 * Hello! I am <b>PowerAct</b>.
 */
@SuppressWarnings("WeakerAccess")
public class PowerAct {

    private static final String TAG = "PowerAct";

    private PowerAct() {
    }

    /**
     * -> {@link PowerAct#lockScreen(Activity, Callback)}
     *
     * @param activity Be used to open specific permission request UI and perform power operations.
     *                 Should not be null.
     */
    public static void lockScreen(Activity activity) {
        lockScreen(activity, null);
    }

    /**
     * Make the device lock immediately, as if the lock screen timeout has expired at the point of this call.
     * <p>
     * Use {@link android.app.admin.DevicePolicyManager} to lock before 28, Since 28 use
     * {@link android.accessibilityservice.AccessibilityService} to lock in order to
     * unlock by biometric sensors.
     *
     * @param activity Be used to open specific permission request UI and perform power operations.
     *                 Should not be null.
     * @param callback To return operation status.
     *                 Can be null.
     * @see DevicePolicyManager#lockNow()
     * @see android.accessibilityservice.AccessibilityService#GLOBAL_ACTION_LOCK_SCREEN
     */
    public static void lockScreen(Activity activity, Callback callback) {
        requestAction(activity, callback, false);
    }

    /**
     * ->{@link PowerAct#showPowerDialog(Activity, Callback)}
     *
     * @param activity Be used to open specific permission request user interfaces and perform power operations.
     *                 Should not be null.
     */
    public static void showPowerDialog(Activity activity) {
        showPowerDialog(activity, null);
    }

    /**
     * To open the power long-press dialog.
     * <b>The operation can only be available since 21.</b>
     *
     * @param activity Be used to open specific permission request UI and perform power operations.
     *                 Should not be null.
     * @param callback To return operation status.
     *                 Can be null.
     * @see android.accessibilityservice.AccessibilityService#GLOBAL_ACTION_POWER_DIALOG
     */
    public static void showPowerDialog(Activity activity, Callback callback) {
        requestAction(activity, callback, true);
    }

    private static void requestAction(Activity activity, Callback callback, boolean isShowPowerDialog) {
        if (activity == null) {
            Log.e(TAG, "requestAction: Activity is null!");
            if (callback != null) callback.failed();
            return;
        }
        if (isShowPowerDialog && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Log.e(TAG, "requestAction: Cannot show power dialog before API level 21!");
            if (callback != null) callback.failed();
            return;
        }
        FragmentManager manager = activity.getFragmentManager();
        PaFragment fragment = (PaFragment) manager.findFragmentByTag(TAG);
        if (fragment == null) {
            Fragment invisibleFragment = new PaFragment();
            FragmentTransaction transaction = manager.beginTransaction().add(invisibleFragment, TAG);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                transaction.commitNow();
            } else {
                transaction.commit();
                manager.executePendingTransactions();
            }
            requestAction(activity, callback, isShowPowerDialog);
        } else {
            fragment.requestAction(callback, isShowPowerDialog);
        }
    }
}