package com.github.iunius118.tolaserblade.client;

import org.jetbrains.annotations.NotNull;

public record LaserBladeTrailGroup(LaserBladeTrail thirdPersonRightHandTrail,
								   LaserBladeTrail thirdPersonLeftHandTrail,
								   LaserBladeTrail firstPersonRightHandTrail,
								   LaserBladeTrail firstPersonLeftHandTrail) {

	public LaserBladeTrailGroup(@NotNull LaserBladeTrail thirdPersonRightHandTrail,
								@NotNull LaserBladeTrail thirdPersonLeftHandTrail,
								@NotNull LaserBladeTrail firstPersonRightHandTrail,
								@NotNull LaserBladeTrail firstPersonLeftHandTrail) {
		this.thirdPersonRightHandTrail = thirdPersonRightHandTrail;
		this.thirdPersonLeftHandTrail = thirdPersonLeftHandTrail;
		this.firstPersonRightHandTrail = firstPersonRightHandTrail;
		this.firstPersonLeftHandTrail = firstPersonLeftHandTrail;
	}

	public LaserBladeTrailGroup(long lifetimeThirdPersonNanos, long lifetimeFirstPersonNanos) {
		this(new LaserBladeTrail(lifetimeThirdPersonNanos), new LaserBladeTrail(lifetimeThirdPersonNanos),
			new LaserBladeTrail(lifetimeFirstPersonNanos), new LaserBladeTrail(lifetimeFirstPersonNanos));
	}

	public LaserBladeTrailGroup(long lifetimeNanos) {
		this(new LaserBladeTrail(lifetimeNanos), new LaserBladeTrail(lifetimeNanos),
			new LaserBladeTrail(lifetimeNanos), new LaserBladeTrail(lifetimeNanos));
	}

	public LaserBladeTrailGroup() {
		this(new LaserBladeTrail(), new LaserBladeTrail(), new LaserBladeTrail(), new LaserBladeTrail());
	}

	public void updateAll(long now) {
		thirdPersonRightHandTrail.update(now);
		thirdPersonLeftHandTrail.update(now);
		firstPersonRightHandTrail.update(now);
		firstPersonLeftHandTrail.update(now);
	}
}
