package me.orangemonkey68.thread.client.gui.hud;

import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreadHud {
    private List<HudWidget> widgets = new ArrayList<>();

    public void removeWidget(HudWidget widget) {
        widgets.remove(widget);
    }

    public void addWidget(HudWidget widget) {
        widgets.add(widget);
    }

    public void render(MatrixStack matrixStack){
        widgets.forEach(widget -> {
            if(widget.isEnabled()){
                widget.render(matrixStack);
            }
        });
    }


}
