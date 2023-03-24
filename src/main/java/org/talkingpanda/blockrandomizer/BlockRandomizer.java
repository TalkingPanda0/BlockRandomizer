package org.talkingpanda.blockrandomizer;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;

import java.util.ArrayList;

import org.joml.Math;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BlockRandomizer implements ModInitializer {
	
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("org.talkingpanda.blockrandomizer");
	public static Boolean isActive = false;
	private static ArrayList<Integer> choices = new ArrayList<Integer>();
	private static int multipliers[] = new int[]{1,1,1,1,5,1,1,1,1};

	private static MinecraftClient client;
	private static KeyBinding KeyBinding; 
	public static void RandomizeBlock(ClientPlayerEntity player)
	{
		PlayerInventory inventory =  player.getInventory();
		inventory.selectedSlot = choices.get((int)(Math.random() * choices.size()));
	}
	private static void GenerateChoices(){
		choices. clear();
		for(int i=0;i<multipliers.length;i++)
		{
			for(int ii=0;ii<multipliers[i];ii++)
			{
				choices.add(i);
			}
		}

	}
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		KeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
					"key.blockrandomizer.start",
					InputUtil.Type.KEYSYM,
					GLFW.GLFW_KEY_F4,
					"category.talkingpanda.start"
				
					)
				);
		client = MinecraftClient.getInstance();
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while(KeyBinding.wasPressed()) {
				
				isActive = !isActive;
				client.inGameHud.setTitle(Text.literal(isActive ? "Block Randomizer: ON" : "Block Randomizer: OFF"));
				if(isActive)
					GenerateChoices();
				LOGGER.info(choices.toString());;
			}
		});
		
		

		LOGGER.info("Item Randomizer Loaded!");
	}
}
