package dev.ultreon.fabricated.terasology;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.metadata.ModEnvironment;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import net.fabricmc.loader.impl.FormattedException;
import net.fabricmc.loader.impl.game.GameProvider;
import net.fabricmc.loader.impl.game.GameProviderHelper;
import net.fabricmc.loader.impl.game.LibClassifier;
import net.fabricmc.loader.impl.game.patch.GameTransformer;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import net.fabricmc.loader.impl.metadata.BuiltinModMetadata;
import net.fabricmc.loader.impl.metadata.ContactInformationImpl;
import net.fabricmc.loader.impl.util.Arguments;
import net.fabricmc.loader.impl.util.ExceptionUtil;
import net.fabricmc.loader.impl.util.SystemProperties;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.fabricmc.loader.impl.util.log.LogHandler;
import org.jetbrains.annotations.NotNull;
import org.terasology.engine.utilities.OS;
import org.terasology.engine.version.TerasologyVersion;

import javax.swing.*;
import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class is the main entry point for the game provider of Terasology.
 *
 * @author <a href="https://github.com/XyperCode">Qubilux</a>
 * @since 0.1.0
 */
@SuppressWarnings({"FieldCanBeLocal", "SameParameterValue", "unused"})
public class TerasologyGameProvider implements GameProvider {
    private static final String[] ALLOWED_EARLY_CLASS_PREFIXES = {"org.apache.logging.log4j.", "dev.ultreon.gameprovider.quantum.", "dev.ultreon.premain."};

    private final GameTransformer transformer = new GameTransformer();
    private EnvType env;
    private Arguments arguments;
    private final List<Path> gameJars = new ArrayList<>();
    private final List<Path> logJars = new ArrayList<>();
    private final List<Path> miscGameLibraries = new ArrayList<>();
    private Collection<Path> validParentClassPath = new ArrayList<>();
    private String entrypoint;
    private boolean log4jAvailable;
    private boolean slf4jAvailable;

    /**
     * Constructor for QuantumVxlGameProvider class.
     * Reads version properties from a file and initializes the versions' property.
     */
    public TerasologyGameProvider() {
        Path launchDirectory = getLaunchDirectory();
        if (Files.notExists(launchDirectory)) {
            try {
                Files.createDirectories(launchDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Get the game ID.
     *
     * @return the game ID
     */
    @Override
    public String getGameId() {
        return "quantum";
    }

    /**
     * Get the game name.
     *
     * @return the game name
     */
    @Override
    public String getGameName() {
        return "Terasology";
    }

    /**
     * Get the raw game version.
     *
     * @return the raw game version
     */
    @Override
    public String getRawGameVersion() {
        return TerasologyVersion.getInstance().getHumanVersion();
    }

    /**
     * Get the normalized game version.
     *
     * @return the normalized game version
     */
    @Override
    public String getNormalizedGameVersion() {
        return TerasologyVersion.getInstance().getDisplayVersion();
    }

    /**
     * Retrieves a collection of BuiltinMods.
     *
     * @return collection of BuiltinMods
     */
    @Override
    public Collection<BuiltinMod> getBuiltinMods() {
        return List.of(
                // Creating a BuiltinMod for Terasology
                new BuiltinMod(this.gameJars, new BuiltinModMetadata.Builder("terasology", TerasologyVersion.getInstance().getengineVersion())
                        .addLicense("Apache-2.0")
                        .addAuthor("Terasology", Map.of("github", "https://github.com/Terasology"))
                        .addIcon(128, "assets/terasology/icon.png")
                        .setEnvironment(ModEnvironment.UNIVERSAL)
                        .setContact(new ContactInformationImpl(Map.of("sources", "https://github.com/Terasology", "email", "contact.ultreon@gmail.com", "homepage", "https://terasology.org")))
                        .setDescription("An open source voxel world - imagine the possibilities!\n\nThe Terasology project was born from a Minecraft-inspired tech demo in 2011. Over the years it has been a playground for different kinds of technical experiments and proof of concepts. The community strives to make Terasology a stable platform for various types of gameplay settings in a voxel world.")
                        .setName("Terasology")
                        .build())
        );
    }

    @Override
    public String getEntrypoint() {
        return this.entrypoint;
    }

    @Override
    public Path getLaunchDirectory() {
        return getDataDir();
    }

    @NotNull
    public static Path getDataDir() {
        return switch (OS.get()) {
            case WINDOWS -> Path.of(System.getProperty("user.home"), "AppData\\Roaming\\Terasology");
            case MACOSX -> Path.of(System.getProperty("user.home"), "Library\\Application Support\\Terasology");
            case LINUX -> {
                var home = System.getProperty("user.home");
                yield Paths.get(home, ".local", "share", "Terasology");
            }
        };
    }

    @Override
    public boolean isObfuscated() {
        return false;
    }

    @Override
    public boolean requiresUrlClassLoader() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Locates the Terasology game.
     *
     * @param launcher the Fabric launcher
     * @param args     the game arguments
     * @return {@code true} if the game was located, {@code false} otherwise
     */
    @Override
    public boolean locateGame(FabricLauncher launcher, String[] args) {
        // Set the environment type and parse the arguments
        this.env = launcher.getEnvironmentType();
        this.arguments = new Arguments();
        this.arguments.parse(args);

        try {
            // Create a new LibClassifier object with the specified class and environment type
            var classifier = new LibClassifier<>(GameLibrary.class, this.env, this);

            // Get the client and server libraries
            var clientLib = GameLibrary.TERASOLOGY_PC;

            // Get the common game jar and check if it is declared
            var clientJar = GameProviderHelper.getCommonGameJar();
            var commonGameJarDeclared = clientJar != null;

            // Process the common game jar if it is declared
            if (commonGameJarDeclared) {
                classifier.process(clientJar);
            }
            List<Exception> suppressedExceptions = new ArrayList<>();

            // Process the launcher class path
            classifier.process(launcher.getClassPath());

            for (Path path : launcher.getClassPath()) {
                System.out.println(path);
            }

            // Get the client and server jars from the classifier
            clientJar = env == EnvType.CLIENT ? classifier.getOrigin(GameLibrary.TERASOLOGY_PC) : null;

            // Warn if the common game jar didn't contain any of the expected classes
            if (commonGameJarDeclared && clientJar == null) {
                Log.warn(LogCategory.GAME_PROVIDER, "The declared common game jar didn't contain any of the expected classes!");
                suppressedExceptions.add(new FormattedException("The declared common game jar didn't contain any of the expected classes!", "The declared common game jar didn't contain any of the expected classes!"));
            }

            // Add the client and server jars to the game jars list
            if (clientJar != null) {
                this.gameJars.add(clientJar);
            } else {
                suppressedExceptions.add(new FormattedException("No client jar found", "No client jar found for Terasology"));
            }

            this.gameJars.addAll(Arrays.stream(GameLibrary.GAME).map(classifier::getOrigin).filter(Objects::nonNull).toList());

            if (this.gameJars.isEmpty()) {
                if (!suppressedExceptions.isEmpty()) {
                    FormattedException noGameJarFound = new FormattedException("No game jar found", "No game jar found for Terasology");

                    for (Exception e : suppressedExceptions) {
                        noGameJarFound.addSuppressed(e);
                    }
                    throw noGameJarFound;
                }
                throw new FormattedException("No game jar found", "No game jar found for Terasology");
            }

            // Get the entry point class name from the classifier
            this.entrypoint = classifier.getClassName(clientLib);

            // Check if log4j and slf4j are available
            this.log4jAvailable = classifier.has(GameLibrary.LOG4J_API) && classifier.has(GameLibrary.LOG4J_CORE);
            this.slf4jAvailable = classifier.has(GameLibrary.SLF4J_API) && classifier.has(GameLibrary.SLF4J_CORE);
            var hasLogLib = this.log4jAvailable || this.slf4jAvailable;

            // Configure the built-in log
            Log.configureBuiltin(hasLogLib, !hasLogLib);

            // Add logging jars to the appropriate lists
            for (var lib : GameLibrary.LOGGING) {
                var path = classifier.getOrigin(lib);

                if (path != null) {
                    if (hasLogLib) {
                        this.logJars.add(path);
                    } else if (!this.gameJars.contains(path)) {
                        this.miscGameLibraries.add(path);
                    }
                }
            }

            // Add unmatched origins to the misc game libraries list
            this.miscGameLibraries.addAll(classifier.getUnmatchedOrigins());

            // Get the valid parent class path from the classifier
            this.validParentClassPath = classifier.getSystemLibraries();

        } catch (IOException e) {
            // Wrap and throw the exception
            throw ExceptionUtil.wrap(e);
        }

        // Expose game jar locations to the FabricLoader share
        var share = FabricLoaderImpl.INSTANCE.getObjectShare();

        share.put("fabric-loader:inputGameJar", this.gameJars.get(0));
        share.put("fabric-loader:inputGameJars", this.gameJars);

        return true;
    }

    /**
     * Initializes the FabricLauncher with necessary configurations.
     *
     * @param launcher The FabricLauncher instance to initialize
     */
    @Override
    public void initialize(FabricLauncher launcher) {
        // Set the valid parent class path
        launcher.setValidParentClassPath(this.validParentClassPath);

        // Load the logger libraries on the platform CL when not in a unit test
        if (!this.logJars.isEmpty() && !Boolean.getBoolean(SystemProperties.UNIT_TEST)) {
            for (var jar : this.logJars) {
                if (this.gameJars.contains(jar)) {
                    launcher.addToClassPath(jar, TerasologyGameProvider.ALLOWED_EARLY_CLASS_PREFIXES);
                } else {
                    launcher.addToClassPath(jar);
                }
            }
        }

        // Setup the log handler
        this.setupLogHandler(launcher, true);

        // Locate entry points using the transformer
        this.transformer.locateEntrypoints(launcher, new ArrayList<>());
    }

    /**
     * Sets up the log handler for the Fabric launcher.
     *
     * @param launcher    the Fabric launcher instance
     * @param useTargetCl true if the target class loader should be used, false otherwise
     */
    private void setupLogHandler(FabricLauncher launcher, boolean useTargetCl) {
        // Disable lookups as they are not used by Terasology and can cause issues with older log4j2 versions
        System.setProperty("log4j2.formatMsgNoLookups", "true");

        try {
            // Specify the class name for the custom log handler
            final var logHandlerClsName = "dev.ultreon.fabricated.terasology.TerasologyLogHandler";

            // Save the previous class loader
            var prevCl = Thread.currentThread().getContextClassLoader();
            Class<?> logHandlerCls;

            // Depending on the flag, use the target class loader or load the class directly
            if (useTargetCl) {
                Thread.currentThread().setContextClassLoader(launcher.getTargetClassLoader());
                logHandlerCls = launcher.loadIntoTarget(logHandlerClsName);
            } else {
                logHandlerCls = Class.forName(logHandlerClsName);
            }

            // Initialize the log handler with the instantiated class
            Log.init((LogHandler) logHandlerCls.getConstructor().newInstance());
            // Restore the previous class loader
            Thread.currentThread().setContextClassLoader(prevCl);
        } catch (ReflectiveOperationException e) {
            // Throw a runtime exception if there is a reflective operation exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public GameTransformer getEntrypointTransformer() {
        return this.transformer;
    }

    @Override
    public boolean hasAwtSupport() {
        return false;
    }

    /**
     * Unlocks the class path for the given FabricLauncher by setting allowed prefixes for gameJars and adding miscGameLibraries to the classpath.
     *
     * @param launcher the FabricLauncher instance
     */
    @Override
    public void unlockClassPath(FabricLauncher launcher) {
        // Set allowed prefixes for gameJars that are logged
        for (var gameJar : this.gameJars) {
            if (this.logJars.contains(gameJar)) {
                launcher.setAllowedPrefixes(gameJar);
            } else {
                launcher.addToClassPath(gameJar);
            }
        }

        // Add miscGameLibraries to the classpath
        for (var lib : this.miscGameLibraries) {
            launcher.addToClassPath(lib);
        }
    }

    /**
     * Returns the first game jar from the list of game jars.
     *
     * @return the first game jar
     */
    public Path getGameJar() {
        return this.gameJars.get(0);
    }

    /**
     * Launches the application using the provided ClassLoader.
     * Sets the user directory to the launch directory.
     * Loads the target class and invokes its main method with the specified arguments.
     *
     * @param loader The ClassLoader to use for loading the target class.
     */
    @Override
    public void launch(ClassLoader loader) {
        // Get the target class to launch
        var targetClass = this.entrypoint;

        MethodHandle invoker;

        try {
            // Load the target class and find the 'main' method handle
            var c = loader.loadClass(targetClass);
            invoker = MethodHandles.lookup().findStatic(c, "main", MethodType.methodType(void.class, String[].class));
        } catch (NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            throw new FormattedException("Failed to start Terasology", e);
        }

        try {
            //noinspection ConfusingArgumentToVarargsMethod
            invoker.invokeExact(this.arguments.toArray());
        } catch (Throwable t) {
            throw new FormattedException("Terasology has crashed", t);
        }
    }

    /**
     * Get the arguments for the method.
     *
     * @return the arguments
     */
    @Override
    public Arguments getArguments() {
        return this.arguments;
    }

    /**
     * Check if the error GUI can be opened.
     *
     * @return true if the error GUI can be opened, false otherwise
     */
    @Override
    public boolean canOpenErrorGui() {
        if (this.arguments == null || this.env == env.CLIENT)
            return true;

        return false;
    }

    /**
     * Get the launch arguments.
     *
     * @param sanitize flag to indicate if the arguments should be sanitized
     * @return an array of launch arguments
     */
    @Override
    public String[] getLaunchArguments(boolean sanitize) {
        return new String[0];
    }
}
