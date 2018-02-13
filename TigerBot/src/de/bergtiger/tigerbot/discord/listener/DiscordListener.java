package de.bergtiger.tigerbot.discord.listener;

import de.bergtiger.tigerbot.TigerBot;
import de.bergtiger.tigerbot.data.MyListener;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DiscordListener extends ListenerAdapter implements MyListener{

	protected TigerBot plugin;
	protected JDA jda;
	protected boolean active = false;
	
	public DiscordListener(TigerBot plugin) {
		this.plugin = plugin;
		this.jda = this.plugin.getJDA();
	}

	@Override
	public void register() {
		if(!this.active) {
			this.jda.addEventListener(this);
			this.plugin.getLogger().info("addListener: " + this.getClass().getSimpleName());
			this.active = true;
		}
	}

	@Override
	public void unregister() {
		if(this.active) {
			this.jda.removeEventListener(this);
			this.plugin.getLogger().info("removeListener: " + this.getClass().getSimpleName());
			this.active = false;
		}
	}
}
