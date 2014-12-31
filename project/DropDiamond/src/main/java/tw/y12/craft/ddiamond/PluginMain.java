package tw.y12.craft.ddiamond;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {

	private static Plugin _pi;

	private void addRecipes() {

		ShapedRecipe yRecipeDiamond = new ShapedRecipe(new ItemStack(
				Material.DIAMOND, 1));
		yRecipeDiamond.shape("D D", "DCD", " C ");
		yRecipeDiamond.setIngredient('D', Material.DIRT);
		yRecipeDiamond.setIngredient('C', Material.COBBLESTONE);

		ShapedRecipe yRecipeTnt = new ShapedRecipe(new ItemStack(
				Material.TNT, 4)).shape("D D", "DCD", "DCD")
				.setIngredient('D', Material.DIRT)
				.setIngredient('C', Material.COBBLESTONE);
		
		getServer().addRecipe(yRecipeTnt);
		getServer().addRecipe(yRecipeDiamond);
	}

	public void onEnable() {
		_pi = this;
		getServer().getPluginManager().registerEvents(new DropEvent(), this);
		addRecipes();
	}

	public static Plugin getPlugin() {
		return _pi;
	}
}
