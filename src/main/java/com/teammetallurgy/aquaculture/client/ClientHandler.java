package com.teammetallurgy.aquaculture.client;

import com.teammetallurgy.aquaculture.Aquaculture;
import com.teammetallurgy.aquaculture.block.tileentity.NeptunesBountyTileEntity;
import com.teammetallurgy.aquaculture.block.tileentity.TackleBoxTileEntity;
import com.teammetallurgy.aquaculture.client.gui.screen.TackleBoxScreen;
import com.teammetallurgy.aquaculture.client.renderer.entity.AquaBobberRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.AquaFishRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.FishMountRenderer;
import com.teammetallurgy.aquaculture.client.renderer.entity.TurtleLandRenderer;
import com.teammetallurgy.aquaculture.client.renderer.tileentity.NeptunesBountyRenderer;
import com.teammetallurgy.aquaculture.client.renderer.tileentity.TackleBoxRenderer;
import com.teammetallurgy.aquaculture.entity.*;
import com.teammetallurgy.aquaculture.init.AquaGuis;
import com.teammetallurgy.aquaculture.init.AquaItems;
import com.teammetallurgy.aquaculture.item.DyeableItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.TippedArrowRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Aquaculture.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {

    public static void setupClient() {
        ScreenManager.registerFactory(AquaGuis.TACKLE_BOX, TackleBoxScreen::new);
        ClientRegistry.bindTileEntitySpecialRenderer(NeptunesBountyTileEntity.class, new NeptunesBountyRenderer<>());
        ClientRegistry.bindTileEntitySpecialRenderer(TackleBoxTileEntity.class, new TackleBoxRenderer<>());
        RenderingRegistry.registerEntityRenderingHandler(AquaFishingBobberEntity.class, AquaBobberRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(AquaFishEntity.class, AquaFishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(WaterArrowEntity.class, TippedArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(TurtleLandEntity.class, TurtleLandRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FishMountEntity.class, FishMountRenderer::new);
        //Item Colors
        ItemColors itemColor = Minecraft.getInstance().getItemColors();
        itemColor.register((stack, tintIndex) -> tintIndex > 0 ? -1 : ((DyeableItem) stack.getItem()).getColor(stack), AquaItems.FISHING_LINE, AquaItems.BOBBER);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModelLoader.addSpecialModel(FishMountRenderer.OAK);
        ModelLoader.addSpecialModel(FishMountRenderer.SPRUCE);
        ModelLoader.addSpecialModel(FishMountRenderer.BIRCH);
        ModelLoader.addSpecialModel(FishMountRenderer.JUNGLE);
        ModelLoader.addSpecialModel(FishMountRenderer.ACACIA);
        ModelLoader.addSpecialModel(FishMountRenderer.DARK_OAK);
    }
}