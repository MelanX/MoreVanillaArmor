package de.melanx.MoreVanillaArmor.tile_entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RedstoneEssenceBlockEntity extends BlockEntity {

    private int tick = 0;

    public RedstoneEssenceBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.REDSTONE_ESSENCE.get(), pos, state);
    }

    public static <T extends BlockEntity> void serverTick(Level level, BlockPos pos, BlockState state, T blockEntity) {
        if (level != null && blockEntity instanceof RedstoneEssenceBlockEntity be) {
            if (be.tick > 40) {
                level.setBlockAndUpdate(be.worldPosition, Blocks.AIR.defaultBlockState());
                be.setRemoved();
            }
            be.tick++;
        }
    }
}
