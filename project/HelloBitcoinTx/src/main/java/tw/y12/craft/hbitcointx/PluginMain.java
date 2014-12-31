package tw.y12.craft.hbitcointx;

import org.bitcoinj.core.AbstractPeerEventListener;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Peer;
import org.bitcoinj.core.PeerEventListener;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionOutput;
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

		ShapedRecipe yRecipeIronIngot = new ShapedRecipe(new ItemStack(
				Material.IRON_INGOT, 4)).shape("D D", "DDD", " D ")
				.setIngredient('D', Material.DIRT);

		getServer().addRecipe(yRecipeIronIngot);
		getServer().addRecipe(yRecipeDiamond);
		runWatcher();
	}

	final private PeerEventListener peerListener = new AbstractPeerEventListener() {

		@Override
		public void onPeerConnected(Peer peer, int peerCount) {
			System.out.println(peer);
		}

		@Override
		public void onTransaction(Peer peer, Transaction t) {
			// System.out.println(t);
			long totalOut = 0;
			for (TransactionOutput out : t.getOutputs()) {
				totalOut += out.getValue().value;
			}
			System.out.println(t.getHashAsString() + ":"
					+ Coin.valueOf(totalOut).toFriendlyString());
		}
	};

	private void runWatcher() {
		getServer().getScheduler().runTaskAsynchronously(this, new Runnable() {
			@Override
			public void run() {
				BitcoinTxWatcher watcher = new BitcoinTxWatcher(peerListener);
				watcher.start();
			}
		});
	}

	public void onEnable() {
		_pi = this;
		getServer().getPluginManager().registerEvents(new PlayerEvent(), this);
		addRecipes();
	}

	public static Plugin getPlugin() {
		return _pi;
	}
}
