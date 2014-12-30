package tw.y12.craft.ddiamond;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class DropEvent implements Listener {

	private List<Block> getArenaBlocks(Location loc, int radius) {
		World w = loc.getWorld();
		int xCoord = (int) loc.getX();
		int zCoord = (int) loc.getZ();
		int YCoord = (int) loc.getY();
		List<Block> tempList = new ArrayList<Block>();
		for (int x = 0; x <= 2 * radius; x++) {
			for (int z = 0; z <= 2 * radius; z++) {
				for (int y = 0; y <= 2 * radius; y++) {
					Block block = w.getBlockAt(xCoord + x, YCoord + y, zCoord
							+ z);
					tempList.add(block);
				}
			}
		}
		return tempList;
	}

	private Block findOneBlock(Location loc, int radius, Material m) {
		World w = loc.getWorld();
		int xCoord = (int) loc.getX();
		int zCoord = (int) loc.getZ();
		int YCoord = (int) loc.getY();
		for (int x = 0; x <= 2 * radius; x++) {
			for (int z = 0; z <= 2 * radius; z++) {
				for (int y = 0; y <= 2 * radius; y++) {
					Block block = w.getBlockAt(xCoord + x, YCoord + y, zCoord
							+ z);
					if (m != null && block.getType() == m) {
						return block;
					}
				}
			}
		}
		return null;
	}

	@EventHandler(ignoreCancelled = false, priority = EventPriority.MONITOR)
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		if (event.isCancelled())
			return;
		Player player = event.getPlayer();
		ItemStack itemStack = event.getItem().getItemStack();
		int value = itemStack.getAmount();
		player.sendMessage(ChatColor.YELLOW + "You got " + value + " "
				+ ChatColor.AQUA + itemStack.getType().name());
		if (itemStack.getType() == Material.COBBLESTONE) {
			switch (value) {
			case 2:
				player.sendMessage(ChatColor.YELLOW + "[Pick2]");
				player.getInventory().addItem(new ItemStack(Material.BREAD, 2));
				break;
			case 3:
				player.sendMessage(ChatColor.YELLOW + "[Pick3]");
				ItemStack dAxe = new ItemStack(Material.DIAMOND_AXE, 1);
				ItemStack dPickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
				ItemStack dSword = new ItemStack(Material.DIAMOND_SWORD, 1);
				player.getInventory().addItem(dAxe, dPickaxe, dSword);
				break;
			case 4:
				player.sendMessage(ChatColor.YELLOW + "[Pick4]");
				ItemStack dHelmet = new ItemStack(Material.DIAMOND_HELMET, 1);
				ItemStack dBoots = new ItemStack(Material.DIAMOND_BOOTS, 1);
				ItemStack dChestplate = new ItemStack(
						Material.DIAMOND_CHESTPLATE, 1);
				ItemStack dLeggings = new ItemStack(Material.DIAMOND_LEGGINGS,
						1);
				player.getInventory().addItem(dHelmet, dBoots, dChestplate,
						dLeggings);
				break;
			case 5:
				Block findDiamondOre = findOneBlock(player.getLocation(), 30,
						Material.DIAMOND_ORE);
				if (findDiamondOre != null) {
					player.sendMessage(ChatColor.YELLOW + "[Diamond]"
							+ findDiamondOre.getLocation());
				}
				break;

			default:
				break;
			}
		}
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent evt) {
		Player player = evt.getPlayer();
		PlayerInventory inventory = player.getInventory();
		inventory.addItem(new ItemStack(Material.IRON_PICKAXE, 1),
				new ItemStack(Material.TORCH, 64));
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		Player player = event.getPlayer();
		ItemStack itemStack = event.getItemDrop().getItemStack();
		player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "Drop "
				+ itemStack);
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
		p.sendMessage(ChatColor.GREEN + p.getName() + " Get the one Diamond!");
		PlayerInventory inventory = p.getInventory();
		ItemStack d = new ItemStack(Material.DIAMOND, 1);
		// ItemStack dPickaxe = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		inventory.addItem(d);
	}
}
