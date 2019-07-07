package com.xzwyx.flutter_mpandroid_chart

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class FlutterMpandroidChartPlugin : MethodCallHandler {
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "flutter_mpandroid_chart")
            channel.setMethodCallHandler(FlutterMpandroidChartPlugin())
        }
    }

    override fun onMethodCall(call: MethodCall, result: Result) {

        when (call.method) {
            "getPlatformVersion" -> {
                result.success("Android ${android.os.Build.VERSION.RELEASE}")
            }
            "initSingleLineChart" -> {
            }
            "showLineChart" -> {
            }
            "updateSingleLineChart" -> {
            }
            "updatePointLineChart" -> {
            }
            else -> {
                result.notImplemented()
            }
        }

    }
}
