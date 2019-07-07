package com.xzwyx.flutter_mpandroid_chart_example;

import io.flutter.plugin.common.PluginRegistry;

public class MyViewFlutterPlugin {
//    MyViewFactory(registrar.messenger()));

    public static void registerWith(PluginRegistry registry) {
        final String key = MyViewFlutterPlugin.class.getCanonicalName();

        if (registry.hasPlugin(key)) return;

        PluginRegistry.Registrar registrar = registry.registrarFor(key);
        registrar.platformViewRegistry().registerViewFactory("plugins.nightfarmer.top/myview", new MyViewFactory(registrar.messenger()));
    }
}

