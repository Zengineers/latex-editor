package model.strategies;

public class StrategyFactory {
	
	public VersionTrackingStrategy createStrategy(String type) {
		
		if (type.equals("volatileStrategy")) {
			return new VolatileStrategy();
		}
		if (type.equals("stableStrategy")) {
			return new StableStrategy();
		}
		return null;
	}
}
