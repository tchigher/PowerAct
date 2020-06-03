package android.os;

import android.annotation.TargetApi;

import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;
import static android.os.Build.VERSION_CODES.JELLY_BEAN_MR1;
import static android.os.Build.VERSION_CODES.LOLLIPOP;
import static android.os.Build.VERSION_CODES.N;
import static android.os.Build.VERSION_CODES.Q;

//IPowerManager.aidl
@TargetApi(Q)
public interface IPowerManager extends IInterface {

    // 14- -> 16     jb-mr0-release
    @TargetApi(ICE_CREAM_SANDWICH)
    void goToSleep(long time);

    // 17 -> 20      jb-mr1-dev l-preview
    @TargetApi(JELLY_BEAN_MR1)
    void goToSleep(long time, int reason);

    // 21+           lollipop-dev
    @TargetApi(LOLLIPOP)
    void goToSleep(long time, int reason, int flags);

    // 14- -> 16     jb-mr0-release
    void reboot(String reason);

    // 17+           jb-mr1-release
    @TargetApi(JELLY_BEAN_MR1)
    void reboot(boolean confirm, String reason, boolean wait);

    // 17 -> 23      jb-mr1-dev marshmallow-mr3-release
    @TargetApi(JELLY_BEAN_MR1)
    void shutdown(boolean confirm, boolean wait);

    // 24+           nougat-dev
    @TargetApi(N)
    void shutdown(boolean confirm, String reason, boolean wait);

    // 24+           nougat-dev
    @TargetApi(N)
    void rebootSafeMode(boolean confirm, boolean wait);


    @SuppressWarnings({"UnnecessaryInterfaceModifier", "unused"})
    public abstract static class Stub extends Binder implements IPowerManager {

        public static IPowerManager asInterface(IBinder var0) {
            return null;
        }
    }
}