#import "FlutterMpandroidChartPlugin.h"
#import <flutter_mpandroid_chart/flutter_mpandroid_chart-Swift.h>

@implementation FlutterMpandroidChartPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterMpandroidChartPlugin registerWithRegistrar:registrar];
}
@end
