/*
 * jebi: A book management software made with JavaFX.
 * 
 * Copyright (c) 2022 Dennis Ko (https://github.com/dennis0324)
 * Copyright (c) 2022 Jaedeok Kim (https://github.com/jdeokkim)
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
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

module io.github.jdeokkim.jebi {
    requires transitive javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires MaterialFX;
	requires com.google.auth;
	requires com.google.auth.oauth2;
	requires google.cloud.core;
	requires google.cloud.firestore;
	requires firebase.admin;

    opens io.github.dennis0324.jebi.gui.controller;
    exports io.github.dennis0324.jebi;
}
