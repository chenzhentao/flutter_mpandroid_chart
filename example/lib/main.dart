import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_mpandroid_chart/flutter_mpandroid_chart.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await FlutterMpandroidChart.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Stack(
          children: <Widget>[
            AndroidView(
              viewType: 'plugins.nightfarmer.top/myview',
              creationParams: {
                "myContent": "通过参数传入的文本内容",
              },
              creationParamsCodec: const StandardMessageCodec(),
              onPlatformViewCreated: onMyViewCreated,
            ),
            RaisedButton(
              child: Text("点击"),
              onPressed: myPross,
            ),
          ],
        ),
      ),
    );
  }

  MethodChannel _channel;

  void onMyViewCreated(int id) {
    _channel = new MethodChannel('plugins.nightfarmer.top/myview_$id');
    setMyViewText("我来了");
  }

  Future<void> setMyViewText(String text) async {
    assert(text != null);
    return _channel.invokeMethod('loadDetailSuccess', text);
  }

  void myPross() {
    setState(() {
      updateMyView();
    });
  }

  Future<void> updateMyView() async {
    return _channel.invokeMethod('updatePoint', "");
  }
}
