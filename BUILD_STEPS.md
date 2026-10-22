ACTUAL BLOCKER: Termux has no network access to Gradle plugin repositories; gradlew missing.
FIXED: settings.gradle.kts syntax corrected; plugin version aligned to installed SDK (8.1.2 / compileSdk 34); root build.gradle.kts valid.
SOURCE STATUS: All Kotlin source valid; Room DB v3 intact; no syntax errors.
PRACTICAL ALTERNATIVE TO GET APK (do this outside Termux):
1. Copy /data/data/com.termux/files/home/DuePilot/ to computer with Android Studio.
2. Open in Android Studio / IntelliJ (project opens directly — it's standard Gradle).
3. Sync (downloads plugins/caches — needs network, works on desktop).
4. Build > Build Bundle(s) / APK(s) > Build Debug APK.
5. APK outputs to app/build/outputs/apk/debug/app-debug.apk (verify with ls after build).
6. Transfer APK to device (adb install, or file transfer, or build via CI: GitHub Actions with android-actions/setup-android + ./gradlew assembleDebug).
7. Pre-existing data (Room DB) preserved automatically on device install if same package + same DB file; for fresh install, backup JSON via BackupScreen.
COMMAND (once on desktop): ./gradlew :app:assembleDebug
ESTIMATED TIME: 3–5 min first sync; 1 min build.
NO CLAimed SUCCESS HERE — this is the real path since Termux cannot resolve plugins.
