/*
 *  WeatherMan, Minecraft bukkit plugin
 *  (c)2012-2016, fromgate, fromgate@gmail.com
 *  https://dev.bukkit.org/projects/weatherman
 *
 *  This file is part of WeatherMan.
 *
 *  WeatherMan is free software: you can redistribute it and/or modify
 *	it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  WeatherMan is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with WeatherMan.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package me.fromgate.weatherman.commands.wm;

import me.fromgate.weatherman.util.BiomeTools;
import me.fromgate.weatherman.util.NMSUtil;
import me.fromgate.weatherman.commands.Cmd;
import me.fromgate.weatherman.commands.CmdDefine;
import me.fromgate.weatherman.playerconfig.PlayerConfig;
import me.fromgate.weatherman.util.lang.M;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

@CmdDefine(command = "weatherman", subCommands = "info", permission = "weatherman.basic",
        description = M.CMD_WALKINFO, shortDescription = "/wm info")
public class WmInfo extends Cmd {
    @Override
    public boolean execute(Player player, String[] args) {
        boolean newMode = !PlayerConfig.isWalkInfoMode(player);
        PlayerConfig.setWalkInfoMode(player, newMode);
        player.sendMessage(M.MSG_WALKINFO.getText() + ": " + M.enDis(newMode));
        if (newMode) {
            Biome b1 = player.getLocation().getBlock().getBiome();
            Biome b2 = NMSUtil.getOriginalBiome(player.getLocation());
            if (b1.equals(b2)) {
                M.MSG_BIOMELOC.print(player, BiomeTools.biomeToString(b1));
            } else {
                M.MSG_BIOMELOC2.print(player, BiomeTools.biomeToString(b1), BiomeTools.biomeToString(b2));
            }
        }
        return true;
    }

}
