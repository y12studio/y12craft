package tw.y12.craft.hbitcointx;

import org.bitcoinj.core.AbstractPeerEventListener;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Peer;
import org.bitcoinj.core.PeerEventListener;
import org.bitcoinj.core.PeerGroup;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.net.discovery.DnsDiscovery;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.utils.BriefLogFormatter;
import org.bitcoinj.utils.Threading;


public class BitcoinTxWatcher {
	
	private PeerGroup peerGroup;

	public BitcoinTxWatcher(PeerEventListener pev) {
		BriefLogFormatter.init();
		NetworkParameters params = MainNetParams.get();
		peerGroup = new PeerGroup(params, null /* no chain */);
        peerGroup.setUserAgent("PeerMonitor", "1.0");
		peerGroup.addPeerDiscovery(new DnsDiscovery(params));
		peerGroup.addEventListener(pev);
	}
	
	public void start(){
		peerGroup.startAsync();
		peerGroup.awaitRunning();
	}
	
	public static void main(String[] args) {
		BitcoinTxWatcher watcher = new BitcoinTxWatcher(new AbstractPeerEventListener(){
			
			@Override
			public void onPeerConnected(Peer peer, int peerCount) {
				System.out.println(peer);
			}
			
			@Override
			public void onTransaction(Peer peer, Transaction t) {
				//System.out.println(t);				
				long totalOut = 0;
				for( TransactionOutput out : t.getOutputs()){
					totalOut += out.getValue().value;
				}
				System.out.println(t.getHashAsString()+ ":" + Coin.valueOf(totalOut).toFriendlyString());
			}
		});
		watcher.start();
	}
	
}
