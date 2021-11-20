package model.strategies;

import java.util.HashMap;

public class VersionTrackingStrategyFactory {
	private HashMap<String, VersionTrackingStrategy> strategies;
	
	public VersionTrackingStrategyFactory() {
		strategies = new HashMap<String, VersionTrackingStrategy>();
		strategies.put("volatileStrategy", new VolatileVersionTrackingStrategy());
		strategies.put("stableStrategy", new StableVersionTrackingStrategy());
	}
	
	public VersionTrackingStrategy createStrategy(String type) {
		return strategies.get(type);
	}
}
