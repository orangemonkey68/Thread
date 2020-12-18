package me.orangemonkey68.thread.client.gui.hud.widgets;

import me.orangemonkey68.thread.Thread;
import me.orangemonkey68.thread.client.ThreadClient;
import me.orangemonkey68.thread.client.gui.hud.HudWidget;
import me.orangemonkey68.thread.config.ThreadConfig;
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Map;

public class ArmorWidget implements HudWidget {
//    ClientPlayerEntity player;
    Vec3d pos;
    boolean enabled;


    public ArmorWidget (ClientPlayerEntity player) {
//        this.player = player;
    }

    @Override
    public void render(MatrixStack matrixStack) {
        MinecraftClient instance = MinecraftClient.getInstance();
        ItemRenderer itemRenderer = instance.getItemRenderer();
        TextRenderer textRenderer = instance.textRenderer;
        ClientPlayerEntity player = instance.player;

        ThreadConfig config = AutoConfig.getConfigHolder(ThreadConfig.class).getConfig();

        int widgetX = config.armorHUDXOffset;
        int widgetY = config.armorHUDYOffset;

        boolean renderDurabilityBars = config.drawArmorDurabilityBar;
        boolean renderDurabilityText = config.drawArmorDurabilityText;

        if(player != null) {
            ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
            ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
            ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
            ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);

            int[] yValues = assignYValues(new ItemStack[]{head, chest, legs, feet});

            int headY = yValues[0] + widgetY;
            int chestY = yValues[1 + widgetY];
            int legsY = yValues[2] + widgetY;
            int feetY = yValues[3] + widgetY;

            itemRenderer.renderGuiItemIcon(head, widgetX, headY);
            itemRenderer.renderGuiItemIcon(chest, widgetX, chestY);
            itemRenderer.renderGuiItemIcon(legs, widgetX, legsY);
            itemRenderer.renderGuiItemIcon(feet, widgetX, feetY);

            if(renderDurabilityBars) {
                itemRenderer.renderGuiItemOverlay(textRenderer, head, widgetX, headY);
                itemRenderer.renderGuiItemOverlay(textRenderer, chest, widgetX, chestY);
                itemRenderer.renderGuiItemOverlay(textRenderer, legs, widgetX, legsY);
                itemRenderer.renderGuiItemOverlay(textRenderer, feet, widgetX, feetY);
            }

            if (head.getItem() instanceof ArmorItem) {
                int durability = ((ArmorItem)head.getItem()).getMaterial().getDurability(EquipmentSlot.HEAD);
                if (renderDurabilityText){
//                    textRenderer.draw(matrixStack, durability - head.getDamage() + "/" + durability, getSpriteWidth(head), 0, 0xFFFFFF);
                    renderDurabilityText(textRenderer, matrixStack, durability, head, headY);
                }
            }

            if (chest.getItem() instanceof ArmorItem) {
                int durability = ((ArmorItem)chest.getItem()).getMaterial().getDurability(EquipmentSlot.CHEST);
                if (renderDurabilityText){
                    renderDurabilityText(textRenderer, matrixStack, durability, chest, chestY);
                }
            }

            if (legs.getItem() instanceof ArmorItem) {
                int durability = ((ArmorItem)legs.getItem()).getMaterial().getDurability(EquipmentSlot.LEGS);
                if (renderDurabilityText){
                    renderDurabilityText(textRenderer, matrixStack, durability, legs, legsY);
                }
            }

            if (feet.getItem() instanceof ArmorItem) {
                int durability = ((ArmorItem)feet.getItem()).getMaterial().getDurability(EquipmentSlot.FEET);
                if (renderDurabilityText){
                    renderDurabilityText(textRenderer, matrixStack, durability, feet, feetY);
                }
            }
        }
    }

    @Override
    public Vec3d getPos() {
        return this.pos;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled =enabled;
    }

    int[] assignYValues(ItemStack[] items){
        if (items.length == 4){
            int[] itemYValues = {0, 0, 0, 0};
            int[] itemHeights = {0, 0, 0, 0};
            for (int i = 0; i < items.length; i++) {
                ItemStack item = items[i];
                if(item != null){
                    if (item != ItemStack.EMPTY){
                        itemHeights[i] = MinecraftClient.getInstance().getItemRenderer().getModels().getSprite(item).getHeight();
                    }
                }
            }
//            itemYValues[1] += itemYValues[0]; // slot 2 = slot 1 + it's own height
//            itemYValues[2] += itemYValues[1]; // slot 3 = (slot 1 + slot 2) + it's own height
//            itemYValues[3] += itemYValues[2]; // slot 4 = (slot 1 + slot 2 + slot 3) + it's own height

            itemYValues[1] = itemHeights[0];
            itemYValues[2] = itemYValues[1] + itemHeights[1];
            itemYValues[3] = itemYValues[2] + itemHeights[2];



            return itemYValues;
        } else {
            throw new IllegalStateException("Array length must be exactly 4");
        }
    }

    int getSpriteWidth(ItemStack stack){
        return MinecraftClient.getInstance().getItemRenderer().getModels().getSprite(stack).getWidth();
    }

    int getSpriteHeight(ItemStack stack){
        return MinecraftClient.getInstance().getItemRenderer().getModels().getSprite(stack).getHeight();
    }

    void renderDurabilityText(TextRenderer textRenderer, MatrixStack matrixStack, int durability, ItemStack item, int itemY){
        textRenderer.draw(matrixStack, durability-item.getDamage() + "/" + durability, getSpriteWidth(item) + AutoConfig.getConfigHolder(ThreadConfig.class).get().armorHUDXOffset, (itemY + ((getSpriteHeight(item)/(float)2)-(textRenderer.fontHeight/(float)2))), 0xFFFFFF);
    }
}
