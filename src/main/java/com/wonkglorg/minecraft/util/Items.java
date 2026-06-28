package com.wonkglorg.minecraft.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public final class Items{
	
	public static ItemStack create(Material material, String itemName, List<String> lore) {
		var itemstack = new ItemStack(material);
		ItemMeta meta = itemstack.getItemMeta();
		meta.displayName(MiniMessage.miniMessage().deserialize(itemName));
		meta.lore(lore.stream().map(MiniMessage.miniMessage()::deserialize).toList());
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public static ItemStack create(Material material, String name) {
		var itemstack = new ItemStack(material);
		ItemMeta meta = itemstack.getItemMeta();
		meta.displayName(MiniMessage.miniMessage().deserialize(name));
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	/**
	 * Renames an ItemStack
	 *
	 * @param item The ItemStack to be renamed
	 * @param name The name to give the ItemStack
	 */
	public static void setName(@NotNull final ItemStack item, @NotNull final Component name) {
		ItemMeta meta = item.getItemMeta();
		meta.displayName(name);
		item.setItemMeta(meta);
	}
	
	/**
	 * Renames an ItemStack
	 *
	 * @param item The ItemStack to be renamed
	 * @param name The name to give the ItemStack
	 */
	public static void setName(@NotNull final ItemStack item, @NotNull final String name) {
		ItemMeta meta = item.getItemMeta();
		meta.displayName(MiniMessage.miniMessage().deserialize(name));
		item.setItemMeta(meta);
	}
	
	/**
	 * Set multiple lines of lore for an ItemStack
	 *
	 * @param item The ItemStack to be given lore
	 * @param lore The lines of lore to be given
	 */
	public static void setLore(@NotNull final ItemStack item, @NotNull final List<Component> lore) {
		ItemMeta meta = item.getItemMeta();
		meta.lore(lore);
		item.setItemMeta(meta);
	}
	
	/**
	 * Add a line of lore to an ItemStack
	 *
	 * @param item The ItemStack to be given lore
	 * @param line The line of lore to add
	 */
	public static void addLore(@NotNull final ItemStack item, @NotNull final Component line) {
		ItemMeta meta = item.getItemMeta();
		List<Component> lore = meta.lore();
		lore = lore == null ? new ArrayList<>() : lore;
		lore.add(line);
		meta.lore(lore);
		item.setItemMeta(meta);
	}
	
	/**
	 * Adds multiple lines of lore to an ItemStack
	 *
	 * @param item The ItemStack to be given lore
	 * @param lines The lines or lore to add
	 */
	public static void addLore(@NotNull final ItemStack item, @NotNull final Iterable<Component> lines) {
		ItemMeta meta = item.getItemMeta();
		List<Component> lore = meta.lore();
		lore = lore == null ? new ArrayList<>() : lore;
		for(var line : lines){
			lore.add(line);
		}
		meta.lore(lore);
		item.setItemMeta(meta);
	}
	
	/**
	 * Sets an item to be unbreakable
	 *
	 * @param item The item to make unbreakable
	 * @param value unbreakable
	 */
	public static void setUnbreakable(@NotNull final ItemStack item, final boolean value) {
		ItemMeta meta = item.getItemMeta();
		meta.setUnbreakable(value);
		item.setItemMeta(meta);
	}
	
	/**
	 * Add an enchantment to an ItemStack
	 *
	 * @param item The ItemStack to be enchanted
	 * @param enchant The Enchantment to add to the ItemStack
	 * @param level The level of the Enchantment
	 */
	public static void addEnchant(@NotNull final ItemStack item, @NotNull final Enchantment enchant, final int level) {
		ItemMeta meta = item.getItemMeta();
		if(level <= 0){
			meta.removeEnchant(enchant);
		} else {
			meta.addEnchant(enchant, level, true);
		}
		item.setItemMeta(meta);
	}
	
	/**
	 * Add an attribute to the item
	 *
	 * @param item The item to have an attribute added
	 * @param attribute The Attribute to be added
	 * @param modifier The AttributeModifier to be added
	 */
	public static void addAttribute(@NotNull final ItemStack item, @NotNull final Attribute attribute, @NotNull final AttributeModifier modifier) {
		ItemMeta meta = item.getItemMeta();
		meta.addAttributeModifier(attribute, modifier);
		item.setItemMeta(meta);
	}
	
	/**
	 * Adds ItemFlags to the item
	 *
	 * @param item The item to add ItemFlags to
	 * @param flags The ItemFlags to add
	 */
	public static void addItemFlags(@NotNull final ItemStack item, final ItemFlag... flags) {
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(flags);
		item.setItemMeta(meta);
	}
	
	/**
	 * Removes  ItemFlags from the item
	 *
	 * @param item The item to add ItemFlags to
	 * @param flags The ItemFlags to add
	 */
	public static void removeItemFlags(@NotNull final ItemStack item, @NotNull final ItemFlag... flags) {
		ItemMeta meta = item.getItemMeta();
		meta.removeItemFlags(flags);
		item.setItemMeta(meta);
	}
	
	/**
	 * Adds persistent data to the item
	 *
	 * @param item The item to add persistent data to
	 * @param key The key to add the data under
	 * @param type The type of the data
	 * @param data The data to store
	 * @param <T> The primary object type
	 * @param <Z> The retrieved object type
	 */
	public static <T, Z> void addPersistentTag(ItemStack item, NamespacedKey key, PersistentDataType<T, Z> type, Z data) {
		ItemMeta meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(key, type, data);
		item.setItemMeta(meta);
	}
	
	/**
	 * Damages an item
	 *
	 * @param item The item to damage
	 * @param amount How much damage to apply
	 * @throws IllegalArgumentException if the item is not damageable
	 */
	public static void damage(ItemStack item, int amount) {
		ItemMeta meta = item.getItemMeta();
		if(meta instanceof Damageable damageable){
			damageable.setDamage(amount);
			item.setItemMeta(meta);
		}
		
	}
	
	/**
	 * @param enchantment the enchant to check for
	 * @param itemStack the item to check
	 * @return if the enchantment is present on the item
	 */
	public static boolean hasEnchant(Enchantment enchantment, ItemStack itemStack) {
		return itemStack.hasItemMeta() && itemStack.getItemMeta().hasEnchants() && itemStack.getItemMeta().hasEnchant(enchantment);
	}
}