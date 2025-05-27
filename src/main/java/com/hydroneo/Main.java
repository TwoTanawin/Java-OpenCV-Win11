package com.hydroneo;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {
    static {
        // Load the native OpenCV library
        try {
            nu.pattern.OpenCV.loadShared();
        } catch (Exception e) {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        }
    }

    public static void convertJpgToWebp(String jpgPath, String webpPath, int quality) throws Exception {
        Mat img = Imgcodecs.imread(jpgPath);

        if (img.empty()) {
            throw new Exception("Image not found at: " + jpgPath);
        }

        boolean success = Imgcodecs.imwrite(webpPath, img,
                new MatOfInt(Imgcodecs.IMWRITE_WEBP_QUALITY, quality));

        if (!success) {
            throw new Exception("Failed to write image to: " + webpPath);
        }

        System.out.println("✅ Converted to WebP: " + webpPath);
    }

    public static void main(String[] args) {
        try {
            System.out.println("✅ OpenCV Loaded. Version: " + Core.VERSION);

            String imagePath = "images/rawshrimp.jpg"; // 🔁 replace with your image path
            String webpFile = "images/output.webp";
            Mat colorImage = Imgcodecs.imread(imagePath);

            if (colorImage.empty()) {
                System.err.println("❌ Could not read the image!");
                return;
            }

            Mat grayImage = new Mat();
            Imgproc.cvtColor(colorImage, grayImage, Imgproc.COLOR_BGR2GRAY);

            // Save or display result
            Imgcodecs.imwrite("images/output_gray.jpg", grayImage);
            System.out.println("✅ Saved grayscale image to output_gray.jpg");

            convertJpgToWebp(imagePath, webpFile, 80);
        } catch (Exception e) {
            System.err.println("❌ Failed to load OpenCV: " + e.getMessage());
        }
    }
}