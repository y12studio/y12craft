package tw.y12.craft.ddiamond;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin{
	
	private static Plugin _pi;

	private void addRecipe(){
		  ShapedRecipe yRecipe=new ShapedRecipe(new ItemStack(Material.DIAMOND,1));
		  yRecipe.shape(new String[]{"X X","XSX"," S "});
		  yRecipe.setIngredient('X',Material.DIRT);
		  yRecipe.setIngredient('D',Material.COBBLESTONE);
		  getServer().addRecipe(yRecipe);
	}

	public void onEnable() {
		_pi = this;
		getServer().getPluginManager().registerEvents(new DropEvent(), this);
		addRecipe();
	}

	public static Plugin getPlugin() {
		return _pi;
	}
}
