package org.talkingpanda.blockrandomizer;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;

import org.talkingpanda.blockrandomizer.BlockRandomizer;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;



public class ClothConfig implements ConfigScreenFactory<Screen> {
	public void saveMultiplier(int m,int i)
	{
		BlockRandomizer.multipliers[i] = m;
	}
	@Override
	public Screen create(Screen parent){
		int multipliers[] = BlockRandomizer.multipliers;
		ConfigBuilder builder = ConfigBuilder.create()
			.setTitle(Text.literal("Block Randomizer Config"))
			.setParentScreen(parent);
  		ConfigEntryBuilder entryBuilder = builder.entryBuilder();
		ConfigCategory category = builder.getOrCreateCategory(Text.literal("Multipliers"));
		for(int a=0;a<multipliers.length;a++)
		{
			final int i = a;

			category.addEntry(entryBuilder.startIntField(Text.literal("Hotbar Slot " + (a+1) + ":" ),multipliers[a])
			.setDefaultValue(1)
			.setMin(0)
			.setSaveConsumer(m -> saveMultiplier(m,i))
			.build());
		}
		return builder.build();
	}
}
