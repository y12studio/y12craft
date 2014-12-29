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
		item.setDurability((short) 1);
		player.getWorld().dropItem(player.getLocation(), item);
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent dead) {
		Player p = dead.getPlayer();
		p.sendMessage(ChatColor.RED + "You are dead " + p.getName());
		p.sendMessage(ChatColor.GREEN + p.getName()
		+ " Get the one Diamond and pickaxe!");
		PlayerInventory inventory = p.getInventory();
		ItemStack gavediamondondeath = new ItemStack(Material.DIAMOND, 1);
		ItemStack gavemediamondpickaxeondeath = new ItemStack(
		Material.DIAMOND_PICKAXE, 1);
		inventory.addItem(gavediamondondeath, gavemediamondpickaxeondeath);
	}
}
