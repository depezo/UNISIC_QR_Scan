# UNISIC QR Scan

A modern Android application for scanning and verifying student digital identity cards via QR codes. Built with Jetpack Compose and Material 3.

## Features

- **High-Performance Scanning**: Powered by CameraX and Google ML Kit for fast and accurate QR code detection.
- **Student Verification**: Real-time student data retrieval via the UNISIC API.
- **Modern UI/UX**: Clean, responsive, and intuitive interface designed with Jetpack Compose.
- **Offline Resilient**: Robust error handling for network issues and camera permissions.

## Tech Stack

- **UI**: Jetpack Compose, Material 3
- **Networking**: Ktor Client (Android)
- **Scanning**: CameraX, ML Kit Barcode Scanning
- **Dependency Injection**: Hilt (Dagger)
- **Architecture**: MVVM with Clean Architecture principles

## Getting Started

### Prerequisites

- Android Studio Koala or newer
- JDK 17+
- Android SDK 24 (Min) to 36 (Target)

### Setup

To protect the API endpoint, the base URL is managed through `local.properties`. Follow these steps to set up your environment:

1.  Clone the repository.
2.  Open the project in Android Studio.
3.  Create or open `local.properties` in the root directory.
4.  Add the following line:
    ```properties
    baseUrl="https://your-api-endpoint.com/api"
    ```
5.  Sync the project with Gradle files.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request or open an issue.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
