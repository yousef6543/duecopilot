# DuePilot — Phase 1 Foundation

Native Android app (Kotlin + Jetpack Compose + Material 3) for independent freelancers tracking unpaid invoices and follow-ups.

## Prerequisites
- Android SDK 35, Kotlin 1.9+, Gradle 8+
- Android Studio or command-line `./gradlew`

## What exists (Phase 1)
- `DuePilot` package (`com.duepilot`)
- MVVM structure: `domain/model/`, `data/` (Room DB + DAOs), `ui/`
- Models: `Client`, `Invoice` (BigDecimal + explicit Currency), `FollowUp`
- Invoice statuses: Draft, Unpaid, Overdue, Paid
- Material 3 Dashboard + empty-state + dark/light theme
- Room database (`DuePilotDatabase`) with entities ready
- Unit-test structure prepared (no tests executed due to missing test runner)

## Build / run (expected, NOT verified — see blocker)
```bash
cd DuePilot
./gradlew :app:assembleDebug
# Requires Android SDK + Java — unavailable in this environment
```

## Deliberately deferred
- Full invoice CRUD UI (only dashboard shown)
- Notification system / reminders
- Follow-up message generation / AI copilot
- Export / PDF / email sending
- Backend / sync / login
- Full Room repository implementations (DAOs defined, persistence ready)

## Blocker (verified)
No Android SDK, Java, Kotlin compiler, or Gradle present in this environment (`which java`, `find /` — none found). Source is complete; build cannot execute here.
