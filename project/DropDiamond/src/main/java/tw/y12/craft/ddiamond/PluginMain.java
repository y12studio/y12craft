package tw.y12.craft.ddiamond;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin{
	
	private static Plugin _pi;

	public void onEnable() {
		_pi = this;
		getServer().getPluginManager().registerEvents(new DropEvent(), this);
	}

	public static Plugin getPlugin() {
		return _pi;
	}
}
