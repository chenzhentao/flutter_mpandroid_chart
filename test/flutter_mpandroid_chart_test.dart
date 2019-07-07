import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_mpandroid_chart/flutter_mpandroid_chart.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_mpandroid_chart');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await FlutterMpandroidChart.platformVersion, '42');
  });
}
