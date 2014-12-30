package tw.y12.craft.ddiamond;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class DropEvent implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
		Player player = evt.getPlayer();
		PlayerInventory inventory = player.getInventory();
		ItemStack dPickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemStack dSword = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemStack dHelmet = new ItemStack(Material.DIAMOND_HELMET, 1);
		ItemStack dBoots = new ItemStack(Material.DIAMOND_BOOTS, 1);
		ItemStack dChestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
		ItemStack dLeggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
		inventory.addItem(dPickaxe, dSword, dHelmet, dBoots, dChestplate,
				dLeggings, new ItemStack(Material.BREAD, 24));

	}

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
