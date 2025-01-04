import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:screenshot/screenshot.dart';
import 'package:path_provider/path_provider.dart';
// import 'package:share_plus/share_plus.dart';

import 'screenshot_widget.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Screenshot',
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget{
  HomePage({super.key});

  final ScreenshotController screenshotController = ScreenshotController();
  static const platform = MethodChannel('com.example.screenshot/instagram');

  Future<void> shareToInstagram(String imagePath)async {
    try{
      await platform.invokeMethod('shareToInstagram', {imagePath: imagePath});
    }on PlatformException catch(e){
      debugPrint("Error: ${e.message}");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Center(child: const Text("Screenshot page")),
        backgroundColor: Colors.deepPurple,
      ),
      body: const ScanMe(),
      floatingActionButton: FloatingActionButton(
        backgroundColor: Colors.deepPurple,
        onPressed: () async {
          final directory = await getTemporaryDirectory();
          final filePath = '${directory.path}/screenshot.jpg';
          final image = await screenshotController.captureFromWidget(ScanMe(),
              pixelRatio: 2);
          if(image != null){
            final file = File(filePath);
            await file.writeAsBytes(image);
            await shareToInstagram(filePath);
          }
          
        },
        child: const Icon(
          Icons.arrow_circle_right_rounded,
          color: Colors.white,),
      ),
    );
  }
}
