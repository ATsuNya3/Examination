package com.ATsuNya3.examination.register;

import com.ATsuNya3.examination.block.MessageBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.ATsuNya3.examination.Examination.MODID;

public class BlockRegister {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final Supplier<Block> MessageRecorder = BLOCKS.register("messagerecorder", () -> new MessageBlock(BlockBehaviour.Properties.of()));//注册一个方块
}
