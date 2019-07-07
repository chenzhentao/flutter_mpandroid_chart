import Flutter
import UIKit

public class SwiftFlutterMpandroidChartPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_mpandroid_chart", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterMpandroidChartPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    result("iOS " + UIDevice.current.systemVersion)
  }
}
