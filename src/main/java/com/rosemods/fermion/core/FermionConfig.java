package com.rosemods.fermion.core;

import com.google.common.collect.Lists;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EventBusSubscriber(modid = Fermion.MODID)
public class FermionConfig {
    public static final Client CLIENT;
    public static final Common COMMON;
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final ForgeConfigSpec COMMON_SPEC;

    public static class Common {
        public final Map<CreativeModeTab, ConfigValue<String>> tabOverrides = new HashMap<>();
        public final ConfigValue<Boolean> hideModdedItemTabs;
        public final ConfigValue<List<? extends String>> hiddenItems;
        public final ConfigValue<List<? extends String>> tabModifiers;
        public final ConfigValue<List<? extends String>> hiddenEnchantments;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.comment("Creative Mode Tab Tweaks").push("tab-tweaks");
            builder.comment("Replace the Item Icon for the vanilla Creative Mode Tabs").push("tab-icons");
            this.tabOverrides.put(CreativeModeTab.TAB_BUILDING_BLOCKS, builder.define("Building Blocks Tab Icon", "minecraft:bricks"));
            this.tabOverrides.put(CreativeModeTab.TAB_DECORATIONS, builder.define("Decorations Tab Icon", "minecraft:peony"));
            this.tabOverrides.put(CreativeModeTab.TAB_REDSTONE, builder.define("Redstone Tab Icon", "minecraft:redstone"));
            this.tabOverrides.put(CreativeModeTab.TAB_TRANSPORTATION, builder.define("Transportation Tab Icon", "minecraft:powered_rail"));
            this.tabOverrides.put(CreativeModeTab.TAB_MISC, builder.define("Misc Tab Icon", "minecraft:lava_bucket"));
            this.tabOverrides.put(CreativeModeTab.TAB_FOOD, builder.define("Foodstuffs Tab Icon", "minecraft:apple"));
            this.tabOverrides.put(CreativeModeTab.TAB_TOOLS, builder.define("Tools Tab Icon", "minecraft:iron_axe"));
            this.tabOverrides.put(CreativeModeTab.TAB_COMBAT, builder.define("Combat Tab Icon", "minecraft:golden_sword"));
            this.tabOverrides.put(CreativeModeTab.TAB_BREWING, builder.define("Brewing Tab Icon", "minecraft:potion"));
            this.tabOverrides.put(CreativeModeTab.TAB_INVENTORY, builder.define("Inventory Tab Icon", "minecraft:chest"));
            this.tabOverrides.put(CreativeModeTab.TAB_SEARCH, builder.define("Search Tab Icon", "minecraft:compass"));
            this.tabOverrides.put(CreativeModeTab.TAB_HOTBAR, builder.define("Saved Hotbar Tab Icon", "minecraft:bookshelf"));
            builder.pop();

            this.hideModdedItemTabs = builder.comment("Hides all modded Creative Mode Tabs. (REQUIRES RESTART)").define("Hide Modded Tabs", false);
            this.hiddenItems = builder.comment("Hides any item in this list from the Creative Mode Inventories. (REQUIRES RESTART)").define("Hidden Items", Lists.newArrayList("minecraft:petrified_oak_slab"));
            this.tabModifiers = builder.comment("Moves any item in this list to any specified item tab (REQUIRES RESTART). values: building_blocks, decorations, redstone, transport, misc, food, tools, combat, brewing").define("Item Tab Modifiers", Lists.newArrayList("minecraft:command_block=redstone", "minecraft:repeating_command_block=redstone", "minecraft:chain_command_block=redstone", "minecraft:command_block_minecart=transport", "minecraft:dragon_egg=misc", "minecraft:spawner=misc", "minecraft:structure_block=redstone"));
            this.hiddenEnchantments = builder.comment("Hides any enchantment from this list from the Creative Mode Inventories").define("Hidden Enchanted Books", Lists.newArrayList());

            builder.pop();
        }

    }

    public static class Client {
        public final ConfigValue<Boolean> dyeableTooltip;
        public final ConfigValue<Boolean> horseArmourTooltip;
        public final ConfigValue<Boolean> foodEffectTooltip;
        public final ConfigValue<List<? extends String>> foodEffectBlackList;
        public final ConfigValue<List<? extends String>> customTooltips;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.comment("Extra tooltips for items that displays helpful information").push("tooltips");
            this.dyeableTooltip = builder.comment("Items that are dyeable with have a tooltip displaying this").define("Dyeable Tooltip", true);
            this.horseArmourTooltip = builder.comment("All Horse Armour items will display their armor stat").define("Horse Armour Tooltip", true);
            this.foodEffectTooltip = builder.comment("Food Item Tooltips display which effects they provide").define("Food Effect Tooltip", true);
            this.foodEffectBlackList = builder.comment("Blacklist of items to display food effects tooltips").define("Food Effect Tooltip BlackList", Lists.newArrayList("farmersdelight:apple_cider", "farmersdelight:cooked_rice", "farmersdelight:bone_broth", "farmersdelight:beef_stew", "farmersdelight:chicken_soup", "farmersdelight:vegetable_soup", "farmersdelight:fish_stew", "farmersdelight:fried_rice", "farmersdelight:pumpkin_soup", "farmersdelight:baked_cod_stew", "farmersdelight:noodle_soup", "farmersdelight:bacon_and_eggs", "farmersdelight:pasta_with_meatballs", "farmersdelight:pasta_with_mutton_chop", "farmersdelight:mushroom_rice", "farmersdelight:roasted_mutton_chops", "farmersdelight:vegetable_noodles", "farmersdelight:steak_and_potatoes", "farmersdelight:ratatouille", "farmersdelight:squid_ink_pasta", "farmersdelight:grilled_salmon", "farmersdelight:roast_chicken", "farmersdelight:stuffed_pumpkin", "farmersdelight:honey_glazed_ham", "farmersdelight:shepherds_pie", "farmersdelight:fruit_salad", "farmersdelight:mixed_salad", "abnormals_delight:slabdish"));
            this.customTooltips = builder.comment("Items In this list will have a custom tooltip you will have to add a translation for").define("Custom Tooltip List", Lists.newArrayList());
            builder.pop();
        }

    }

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        final Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);

        COMMON = commonSpecPair.getLeft();
        COMMON_SPEC = commonSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
        CLIENT_SPEC = clientSpecPair.getRight();
    }

}
