package tech.arno.mocha.utils;

import android.content.ComponentName;

import androidx.fragment.app.FragmentActivity;
import androidx.navigation.ActivityNavigator;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;

import java.util.HashMap;

import tech.arno.libcommon.global.AppGlobals;
import tech.arno.mocha.model.Destination;
import tech.arno.mocha.navigator.FixFragmentNavigator;

/**
 * <pre>
 *     author: xuxin
 *     time  : 2020/7/2
 *     desc  :
 * </pre>
 */
public class NavGrapBuilder {
    /**
     * 将自定义的节点数据组装成navGraph
     *
     * @param controller
     */
    public static void build(NavController controller, FragmentActivity activity, int containerId) {
        NavigatorProvider provider = controller.getNavigatorProvider();

//        FragmentNavigator fragmentNavigator = provider.getNavigator(FragmentNavigator.class);
        FixFragmentNavigator fragmentNavigator = new FixFragmentNavigator(activity, activity.getSupportFragmentManager(), containerId);
        provider.addNavigator(fragmentNavigator);

        ActivityNavigator activityNavigator = provider.getNavigator(ActivityNavigator.class);

        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));

        HashMap<String, Destination> destConfig = AppConfig.getDestConfig();
        for (Destination value : destConfig.values()) {
            //按照需要的数据组装
            if (value.isIsFragment()) {
                FragmentNavigator.Destination destination = fragmentNavigator.createDestination();
                destination.setClassName(value.clazzName);
                destination.setId(value.id);
                destination.addDeepLink(value.pageUrl);

                navGraph.addDestination(destination);
            } else {
                ActivityNavigator.Destination destination = activityNavigator.createDestination();
                destination.setId(value.id);
                destination.addDeepLink(value.pageUrl);
                destination.setComponentName(new ComponentName(AppGlobals.getApplication().getPackageName(), value.clazzName));

                navGraph.addDestination(destination);
            }

            //如果是默认启动页
            if (value.asStarter) {
                navGraph.setStartDestination(value.id);
            }
        }

        //最后填入解析并组装好的navGraph
        controller.setGraph(navGraph);
    }
}