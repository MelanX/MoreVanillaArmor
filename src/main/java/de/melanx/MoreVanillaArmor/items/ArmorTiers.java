package de.melanx.MoreVanillaArmor.items;

import de.melanx.MoreVanillaArmor.Config;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public enum ArmorTiers implements IArmorMaterial {

    BONE(Config.materials.get(Config.DefaultMaterial.BONE), SoundEvents.ENTITY_SKELETON_AMBIENT, () -> Ingredient.fromTag(Tags.Items.BONES)),
    COAL(Config.materials.get(Config.DefaultMaterial.COAL), SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, () -> Ingredient.fromItems(Items.COAL), () -> Ingredient.fromTag(Tags.Items.STORAGE_BLOCKS_COAL)),
    EMERALD(Config.materials.get(Config.DefaultMaterial.EMERALD), SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, () -> Ingredient.fromTag(Tags.Items.GEMS_EMERALD)),
    ENDER(Config.materials.get(Config.DefaultMaterial.ENDER), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, () -> Ingredient.fromTag(Tags.Items.ENDER_PEARLS), () -> Ingredient.fromTag(Tags.Items.END_STONES)),
    FIERY(Config.materials.get(Config.DefaultMaterial.FIERY), SoundEvents.ENTITY_BLAZE_SHOOT, () -> Ingredient.fromItems(Items.MAGMA_BLOCK)),
    GLOWSTONE(Config.materials.get(Config.DefaultMaterial.GLOWSTONE), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, () -> Ingredient.fromTag(Tags.Items.DUSTS_GLOWSTONE), () -> Ingredient.fromItems(Items.GLOWSTONE)),
    LAPIS(Config.materials.get(Config.DefaultMaterial.LAPIS), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, () -> Ingredient.fromTag(Tags.Items.GEMS_LAPIS), () -> Ingredient.fromTag(Tags.Items.STORAGE_BLOCKS_LAPIS)),
    NETHER(Config.materials.get(Config.DefaultMaterial.NETHER), SoundEvents.BLOCK_LAVA_EXTINGUISH, () -> Ingredient.fromItems(Items.NETHER_BRICK)),
    OBSIDIAN(Config.materials.get(Config.DefaultMaterial.OBSIDIAN), SoundEvents.ENTITY_ENDER_EYE_DEATH, () -> Ingredient.fromTag(Tags.Items.OBSIDIAN)),
    PAPER(Config.materials.get(Config.DefaultMaterial.PAPER), SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, () -> Ingredient.fromItems(Items.PAPER)),
    PRISMARINE(Config.materials.get(Config.DefaultMaterial.PRISMARINE), SoundEvents.BLOCK_WATER_AMBIENT, () -> Ingredient.fromTag(Tags.Items.DUSTS_PRISMARINE), () -> Ingredient.fromItems(Items.PRISMARINE)),
    QUARTZ(Config.materials.get(Config.DefaultMaterial.QUARTZ), SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA, () -> Ingredient.fromTag(Tags.Items.GEMS_QUARTZ), () -> Ingredient.fromTag(Tags.Items.STORAGE_BLOCKS_QUARTZ)),
    REDSTONE(Config.materials.get(Config.DefaultMaterial.REDSTONE), SoundEvents.ITEM_ARMOR_EQUIP_GOLD, () -> Ingredient.fromTag(Tags.Items.DUSTS_REDSTONE), () -> Ingredient.fromTag(Tags.Items.STORAGE_BLOCKS_REDSTONE)),
    SLIME(Config.materials.get(Config.DefaultMaterial.SLIME), SoundEvents.BLOCK_SLIME_BLOCK_STEP, () -> Ingredient.fromTag(Tags.Items.SLIMEBALLS), () -> Ingredient.fromItems(Items.SLIME_BLOCK)),
    STONE(Config.materials.get(Config.DefaultMaterial.STONE), SoundEvents.BLOCK_STONE_BREAK, () -> Ingredient.fromTag(Tags.Items.STONE)),
    WOOD(Config.materials.get(Config.DefaultMaterial.WOOD), SoundEvents.BLOCK_WOODEN_DOOR_OPEN, () -> Ingredient.fromTag(ItemTags.LOGS));

    private static final int[] DURABILITY_ARRAY = new int[]{13, 15, 16, 11}; // vanilla copy [boots, leggings, chest plate, head]
    private final String name;
    private final int durabilityFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyValue<Ingredient> repairMaterial;
    private final LazyValue<Ingredient> ingredient;

    ArmorTiers(Config.Material material, SoundEvent sound, Supplier<Ingredient> repairMaterial, Supplier<Ingredient> ingredient) {
        this.name = material.getName();
        this.durabilityFactor = material.getDurabilityFactor();
        this.damageReductionAmountArray = material.getDamageReduction();
        this.enchantability = material.getEnchantability();
        this.soundEvent = sound;
        this.toughness = material.getToughness();
        this.knockbackResistance = material.getKnockbackResistance();
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.ingredient = new LazyValue<>(ingredient);
    }

    ArmorTiers(Config.Material material, SoundEvent sound, Supplier<Ingredient> repairMaterial) {
        this.name = material.getName();
        this.durabilityFactor = material.getDurabilityFactor();
        this.damageReductionAmountArray = material.getDamageReduction();
        this.enchantability = material.getEnchantability();
        this.soundEvent = sound;
        this.toughness = material.getToughness();
        this.knockbackResistance = material.getKnockbackResistance();
        this.repairMaterial = new LazyValue<>(repairMaterial);
        this.ingredient = new LazyValue<>(repairMaterial);
    }

    public int getDurability(EquipmentSlotType slotIn) {
        return DURABILITY_ARRAY[slotIn.getIndex()] * this.durabilityFactor;
    }

    public int getDamageReductionAmount(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    @Nonnull
    public SoundEvent getSoundEvent() {
        return this.soundEvent;
    }

    @Nonnull
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }

    @Nonnull
    public String getName() {
        return this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    @Nonnull
    public Ingredient getIngredient() {
        return this.ingredient.getValue();
    }

    @Nonnull
    public ArmorTiers getType() {
        return this;
    }

}
