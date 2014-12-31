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

import com.google.common.collect.Ordering;

public class DropEvent implements Listener {

	private List<Block> findNearBlocks(Location loc, int radius, Material m) {
		// http://minecraft.gamepedia.com/Coordinates
		World w = loc.getWorld();
		int xCoord = (int) loc.getX();
		int zCoord = (int) loc.getZ();
		int yCoord = (int) loc.getY();
		List<Block> tempList = new ArrayList<Block>();
		for (int x = -radius; x < radius; x++) {
			for (int z = -radius; z < radius; z++) {
				for (int y = -radius; y < radius; y++) {
					if (yCoord + y > 0 && Math.abs(y)>1 && Math.abs(z)>1 && Math.abs(x)>1) {
						Block blockPlus = w.getBlockAt(xCoord + x, yCoord + y,
								zCoord + z);
						if (blockPlus != null) {
							if (m != null && blockPlus.getType() == m) {
								tempList.add(blockPlus);
							} else {
								tempList.add(blockPlus);
							}
						}
					}
				}
			}
		}
		return tempList;
	}

	private double getDist(Location loc, Location bloc) {
		double dx = loc.getX() - bloc.getX();
		double dy = loc.getY() - bloc.getY();
		double dz = loc.getZ() - bloc.getZ();
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	private Block findNearestBlock(final Location loc, int radius, Material m) {
		List<Block> blist = findNearBlocks(loc, radius, m);
		if (blist != null && blist.size() > 0) {
			Ordering<Block> o = new Ordering<Block>() {
				@Override
				public int compare(Block left, Block right) {
					return Double.compare(getDist(loc, left.getLocation()),
							getDist(loc, right.getLocation()));
				}
			};
			return o.min(blist);
		} else {
			return null;
		}
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
		if (itemStack.getType() == Material.DIAMOND) {
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
				Block dOre = findNearestBlock(player.getLocation(), 100,
						Material.DIAMOND_ORE);
				if (dOre != null) {
					player.sendMessage(ChatColor.YELLOW + "[Get "
							+ dOre.getType().name() + "]" + dOre.getLocation());
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
		inventory.addItem(new ItemStack(Material.DIAMOND, 9), new ItemStack(
				Material.IRON_PICKAXE, 1), new ItemStack(Material.TORCH, 64));
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
