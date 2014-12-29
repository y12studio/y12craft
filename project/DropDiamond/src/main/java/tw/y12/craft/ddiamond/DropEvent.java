package tw.y12.craft.ddiamond;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class DropEvent implements Listener {

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		ItemStack item = new ItemStack(Material.DIAMOND);
		item.setDurability((short) 12);
		player.getWorld().dropItem(player.getLocation(), item);
	}
}
