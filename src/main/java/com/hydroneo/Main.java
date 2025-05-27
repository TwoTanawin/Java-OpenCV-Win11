package com.hydroneo;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Main {
    static {
        // Load the native OpenCV library
        try {
            // This loads the native library from the org.openpnp.opencv JAR
            nu.pattern.OpenCV.loadShared();
        } catch (Exception e) {
            // Fallback: try loading with system property
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("✅ OpenCV Loaded. Version: " + Core.VERSION);

            // You can now use OpenCV functions here
            // For example:
             Mat mat = new Mat(3, 3, CvType.CV_8UC1);
             System.out.println("Created a 3x3 matrix: " + mat.dump());
        } catch (Exception e) {
            System.err.println("❌ Failed to load OpenCV: " + e.getMessage());
        }
    }
}