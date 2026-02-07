@echo off
REM Run build-ohos.ps1 with ExecutionPolicy Bypass (avoids "script execution disabled" error)
powershell -NoProfile -ExecutionPolicy Bypass -File "%~dp0build-ohos.ps1" %*
exit /b %ERRORLEVEL%
