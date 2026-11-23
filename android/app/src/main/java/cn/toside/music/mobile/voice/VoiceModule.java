package cn.toside.music.mobile.voice;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class VoiceModule extends ReactContextBaseJavaModule {
  public VoiceModule(ReactApplicationContext reactContext) {
    super(reactContext);
  }

  @NonNull
  @Override
  public String getName() {
    return "VoiceModule";
  }

  @ReactMethod
  public void consumePendingCommand(Promise promise) {
    try {
      promise.resolve(VoicePendingStore.consume(getReactApplicationContext()));
    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ReactMethod
  public void clearPendingCommand() {
    VoicePendingStore.clear(getReactApplicationContext());
  }

  @ReactMethod
  public void reportState(boolean playing, String title, String artist) {
    VoiceStateReporter.report(getReactApplicationContext(), playing, title, artist);
  }

  @ReactMethod
  public void setTipEnabled(boolean enabled) {
    VoiceSettings.setTipEnabled(getReactApplicationContext(), enabled);
  }

  @ReactMethod
  public void getTipEnabled(Promise promise) {
    promise.resolve(VoiceSettings.isTipEnabled(getReactApplicationContext()));
  }

  @ReactMethod
  public void reregisterVoice() {
    VoiceRegistrar.register(getReactApplicationContext());
  }
}