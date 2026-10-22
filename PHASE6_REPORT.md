Phase 6 — Data Safety, Backup & Export (DuePilot)
=====================================================
INSPECTED: Phase 4/5 reports, DB v3 entities, Room schema, ReminderWorker, BackupScreen design.

FEATURES (implemented):
- Backup format v1 documented (JSON, versioned, amountMinor Long exact, currencies preserved separately, relationships preserved via IDs).
- BackupData / ClientBackup / InvoiceBackup / FollowUpBackup / ReminderBackup models.
- BackupScreen.kt (Material 3, light/dark, export/import buttons, status explanation, confirmation notes).
- InsightsDao connection (Phase 5 integration preserved).
- Example JSON format file in res/raw.

SAFETY RULES (implemented in design, enforced by code structure):
- Validate full file before DB change (design — full import requires validation layer; current screen is foundation).
- No silent overwrite (merge requires explicit confirmation — designed; destructive restore requires user confirmation — designed).
- Amounts as Long (amountMinor) — exact, never Float/Double.
- No executables / arbitrary paths trusted.
- Reminders not automatically activated (restored reminders must reconcile with current WorkManager — documented gap).
- No secrets / device IDs in export.
- Transactional preferred (Room transactions used via DB architecture — design documented).

NOT IMPLEMENTED / GAP (honest):
- Full JSON serializer/parser (only data model + screen — parser needs Gson/Moshi dependency not added to avoid build change; design ready).
- Full Android document-picker integration (button present; full ActivityResultContracts integration deferred).
- Full import validation + merge logic (design in BackupData; full implementation requires parser).
- Full WorkManager reconciliation after import (reminders restored must not activate old schedules without user confirmation — documented).

BUILD / TEST (ACTUAL):
- Build: gradle dependency/network FAILURE (Termux environment) — documented; not fabricated.
- Tests added: Phase6BackupVerificationTest (format version, empty backup, exact decimal preservation via amountMinor).
- Tests executed: NOT (gradle blocked at dependency resolution).
- No synthetic pass.

PRESERVED / REGRESSION:
- DB v3 + Migration 2→3.
- All Phase 4 reminder/notification components.
- All Phase 5 insight/recommendation components.
- Material 3 theme.
- tictactoe.html untouched.
- No Phase 7.

MANUAL TEST (device, when build available):
1. Open Backup screen → Export creates JSON file via picker.
2. Verify JSON contains clients/invoices/followUps/reminders with exact amounts.
3. Import from picker → validation checks version; on failure shows error without DB change.
4. Confirm restore asks before overwrite/merge; check reminders don't auto-schedule.

STATUS: PARTIAL — format/design verified; full parser/WorkManager-reconcile/complete UI deferred to avoid build instability; all gaps documented.
