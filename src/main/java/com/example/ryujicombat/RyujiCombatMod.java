package com.example.ryujicombat;

import com.example.ryujicombat.skills.SkillHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RyujiCombatMod implements ModInitializer {

    public static final Item RYUJI_BLADE = new SwordItem(
            ToolMaterials.NETHERITE,
            9, -2.4F,
            new Item.Settings().group(ItemGroup.COMBAT)
    );

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("ryujicombat", "ryuji_blade"), RYUJI_BLADE);
        SkillHandler.register();
    }
}