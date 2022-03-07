/*
 * VitalPlayerWeather is a Spigot Plugin that gives players the ability to change their weather.
 * Copyright © 2022 Leopold Meinel & contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://github.com/TamrielNetwork/VitalPlayerWeather/blob/main/LICENSE
 */

package com.tamrielnetwork.vitalplayerweather.utils.commands;

import com.tamrielnetwork.vitalplayerweather.utils.Chat;
import org.bukkit.WeatherType;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CmdSpec {

	public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String perm) {

		if (Cmd.isInvalidSender(sender)) {
			return true;
		}
		return Cmd.isNotPermitted(sender, perm);
	}

	public static boolean isInvalidCmd(@NotNull CommandSender sender, @NotNull String arg, @NotNull String perm) {

		if (Cmd.isInvalidSender(sender)) {
			return true;
		}
		if (Cmd.isNotPermitted(sender, perm)) {
			return true;
		}
		return isInvalidWeatherType(sender, arg);
	}

	public static WeatherType getWeatherType(@NotNull String arg) {

		switch (arg) {
			case "sun", "clear" -> {
				return WeatherType.CLEAR;
			}
			case "storm", "rain" -> {
				return WeatherType.DOWNFALL;
			}
			default -> {
				return null;
			}
		}
	}

	public static List<String> getNames() {

		return new ArrayList<>(Arrays.asList("sun", "clear", "storm", "rain"));
	}

	private static boolean isInvalidWeatherType(@NotNull CommandSender sender, @NotNull String arg) {

		if (getWeatherType(arg) == null) {
			Chat.sendMessage(sender, "invalid-weather");
			return true;
		}
		return false;
	}

}
