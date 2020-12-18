package me.orangemonkey68.thread.client.gui.hud;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

public interface HudWidget {
    void render(MatrixStack matrixStack);

    Vec3d getPos();

    boolean isEnabled();
    void setEnabled(boolean enabled);
}
