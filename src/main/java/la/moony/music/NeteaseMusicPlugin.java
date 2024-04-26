package la.moony.music;

import org.springframework.stereotype.Component;
import run.halo.app.plugin.BasePlugin;
import run.halo.app.plugin.PluginContext;

/**
 * @author moony
 * @url https://moony.la
 * @date 2024/4/3
 */
@Component
public class NeteaseMusicPlugin extends BasePlugin {

    public NeteaseMusicPlugin(PluginContext pluginContext) {
        super(pluginContext);
    }

    @Override
    public void start() {
        System.out.println("插件启动成功！");
    }

    @Override
    public void stop() {
        System.out.println("插件停止！");
    }
}
