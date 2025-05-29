/*
 * Copyright 2016 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.ultreon.fabricated.terasology;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.impl.game.LibClassifier.LibraryType;

enum GameLibrary implements LibraryType {
    TERASOLOGY_PC("org/terasology/engine/Terasology.class"),
    TERASOLOGY_ENGINE("org/terasology/engine/game/Game.class"),
    TERASOLOGY_CR("crashreporter.properties"),
    TERASOLOGY_CR_CORE("org/terasology/crashreporter/CrashReporter.class"),
    TERASOLOGY_GESTALT_CORE("org/terasology/gestalt/assets/Asset.class"),
    TERASOLOGY_GESTALT_ENTITY_SYSTEM("org/terasology/gestalt/entitysystem/entity/EntityManager.class"),
    TERASOLOGY_GESTALT_MODULE("org/terasology/gestalt/module/Module.class"),
    TERASOLOGY_GESTALT_UTIL("org/terasology/gestalt/util/Varargs.class"),
    TERASOLOGY_JN_BULLET("com/badlogic/gdx/physics/bullet/Bullet.class"),
    TERASOLOGY_JN_LUA("org/terasology/jnlua/LuaError.class"),
    TERASOLOGY_JOML_GEOM("org/terasology/joml/geom/AABBf.class"),
	TERASOLOGY_NUI("org/terasology/nui/Color.class"),
	TERASOLOGY_NUI_GESTALT("org/terasology/nui/asset/UIData.class"),
	TERASOLOGY_NUI_INPUT("org/terasology/input/Input.class"),
	TERASOLOGY_NUI_REFLECT("org/terasology/reflection/ReflectionUtil.class"),
	TERASOLOGY_SUBSYSTEMS("org/terasology/persistence/serializers/Serializer.class"),
	TERASOLOGY_REFLECTIONS("org/reflections/Reflections.class"),
	TERASOLOGY_SPLASH("org/terasology/splash/SplashScreen.class"),
	TERASOLOGY_TERA_MATH("org/terasology/math/TeraMath.class"),
	LOG4J_API("org/apache/logging/log4j/LogManager.class"),
	LOG4J_CORE("META-INF/services/org.apache.logging.log4j.spi.Provider", "META-INF/log4j-provider.properties"),
	LOG4J_CONFIG("log4j2.xml"),
	GSON("com/google/gson/TypeAdapter.class"), // used by log4j plugins
	SLF4J_API("org/slf4j/Logger.class"),
	SLF4J_CORE("META-INF/services/org.slf4j.spi.SLF4JServiceProvider");

	static final GameLibrary[] GAME = {
			GameLibrary.TERASOLOGY_PC,
			GameLibrary.TERASOLOGY_ENGINE,
			GameLibrary.TERASOLOGY_CR,
			GameLibrary.TERASOLOGY_CR_CORE,
			GameLibrary.TERASOLOGY_GESTALT_CORE,
			GameLibrary.TERASOLOGY_GESTALT_ENTITY_SYSTEM,
			GameLibrary.TERASOLOGY_GESTALT_MODULE,
			GameLibrary.TERASOLOGY_GESTALT_UTIL,
			GameLibrary.TERASOLOGY_JN_BULLET,
			GameLibrary.TERASOLOGY_JN_LUA,
			GameLibrary.TERASOLOGY_JOML_GEOM,
			GameLibrary.TERASOLOGY_NUI,
			GameLibrary.TERASOLOGY_NUI_GESTALT,
			GameLibrary.TERASOLOGY_NUI_INPUT,
			GameLibrary.TERASOLOGY_NUI_REFLECT,
			GameLibrary.TERASOLOGY_SUBSYSTEMS,
			GameLibrary.TERASOLOGY_REFLECTIONS,
			GameLibrary.TERASOLOGY_SPLASH,
			GameLibrary.TERASOLOGY_TERA_MATH,
	};
	static final GameLibrary[] LOGGING = {
			GameLibrary.LOG4J_API,
			GameLibrary.LOG4J_CORE,
			GameLibrary.LOG4J_CONFIG,
			GameLibrary.GSON,
			GameLibrary.SLF4J_API,
			GameLibrary.SLF4J_CORE
	};

	private final EnvType env;
	private final String[] paths;

	GameLibrary(String path) {
		this(null, new String[] { path });
	}

	GameLibrary(String... paths) {
		this(null, paths);
	}

	GameLibrary(EnvType env, String... paths) {
		this.paths = paths;
		this.env = env;
	}

	@Override
	public boolean isApplicable(EnvType env) {
		return this.env == null || this.env == env;
	}

	@Override
	public String[] getPaths() {
		return paths;
	}
}
