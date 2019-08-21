package host.exp.exponent.notifications;

import android.content.Context;

import org.unimodules.core.ExportedModule;
import org.unimodules.core.ModuleRegistry;
import com.facebook.react.bridge.Promise;
import org.unimodules.core.interfaces.ExpoMethod;
import org.unimodules.interfaces.taskManager.TaskManagerInterface;

import java.util.Map;

public class NotificationBackgroundModule extends ExportedModule {
  private TaskManagerInterface mTaskManager;

  public NotificationBackgroundModule(Context context) {
    super(context);
  }

  @Override
  public String getName() {
    return "ExpoNotificationBackground";
  }

  @Override
  public void onCreate(ModuleRegistry moduleRegistry) {
    mTaskManager = moduleRegistry.getModule(TaskManagerInterface.class);
  }

  @ExpoMethod
  public void registerTaskAsync(String taskName, Map<String, Object> options, final Promise promise) {
    try {
      mTaskManager.registerTask(taskName, NotificationBackgroundTaskConsumer.class, options);
      promise.resolve(null);
    } catch (Exception e) {
      promise.reject(e);
    }
  }

  @ExpoMethod
  public void unregisterTaskAsync(String taskName, final Promise promise) {
    try {
      mTaskManager.unregisterTask(taskName, NotificationBackgroundTaskConsumer.class);
      promise.resolve(null);
    } catch (Exception e) {
      promise.reject(e);
    }
  }
}