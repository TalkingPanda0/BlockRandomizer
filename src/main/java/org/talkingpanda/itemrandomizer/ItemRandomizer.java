package org.talkingpanda.itemrandomizer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemRandomizer implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	private static KeyBinding KeyBinding; 
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		KeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
					"key.itemrandomizer.start",
					InputUtil.Type.KEYSYM,
					GLFW.GLFW_KEY_R,
					"category.talkingpanda.start"
				
					)
				);
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while(KeyBinding.wasPressed()) {
				client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);
			}
		});
		
		

		LOGGER.info("Item Randomizer Loaded!");
	}
}
