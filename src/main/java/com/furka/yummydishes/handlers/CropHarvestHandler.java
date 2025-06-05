package com.furka.yummydishes.handlers;

import com.furka.yummydishes.YummyDishes;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CropBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = YummyDishes.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CropHarvestHandler {

    @SubscribeEvent
    public static void onRightClickCrop(PlayerInteractEvent.RightClickBlock event) {
        Level world = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);
        Player player = event.getEntity();

        if (state.getBlock() instanceof CropBlock crop && !world.isClientSide()) {
            if (crop.isMaxAge(state)) {
                List<ItemStack> drops = Block.getDrops(
                    state,
                    (ServerLevel) world,
                    pos,
                    null,
                    player,
                    player.getItemInHand(InteractionHand.MAIN_HAND)
                );

                drops.forEach(stack -> Block.popResource(world, pos, stack));

                world.setBlock(pos, crop.getStateForAge(0), Block.UPDATE_CLIENTS);

                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
        }
    }
}
